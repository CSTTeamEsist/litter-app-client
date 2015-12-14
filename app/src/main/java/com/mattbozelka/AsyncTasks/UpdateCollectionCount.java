package com.mattbozelka.AsyncTasks;

/*
*
* UpdateCollectionCount
*
* Background Task
*
* Uses a RESTful POST connection to the server that sends a JSON object containing the information
* needed to update the count of a litterPiece in the DB. It doesn't expect anything in return
* but tests to check if it was successful before updating the UI.
*
* Constructor expects:
* LitterPiece lp - a LitterPiece object in the list that needs to be updated,
* String volID - Volunteer ID to update the correct users list,
* String teamID - team ID (will be used in future uses),
* String eventID - the ID of the event the litter came from,
* boolean addTo - boolean to say if a piece should be addded or subtracted, true for add, and false
*   for removal
* LitterListAdapter mLitterListAdapter - Custom List adapter to update on a successful operation
*   so the UI gets updated as well.
*
* */

import android.os.AsyncTask;
import android.util.Log;

import com.mattbozelka.com.mattbozelka.custom.adapters.LitterListAdapter;
import com.mattbozelka.model.LitterPiece;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateCollectionCount extends AsyncTask<Void, Void, Boolean> {

    private final String LOG_TAG = UpdateCollectionCount.class.getSimpleName();
    private final String LITTER_ID_TAG = "litterID";
    private final String VOL_ID_TAG = "volID";
    private final String TEAM_ID_TAG = "teamID";
    private final String EVENT_ID_TAG = "eventID";
    private final String TALLY_TAG = "tally";
    private String CREATE_USER_BASE ="http://cstserver2a.bitnamiapp.com/litter-service-webapp/webapi/" +
            "litter-collection-list/create/";

    private LitterPiece litterPiece;
    private String volID;
    private String teamID;
    private String eventID;
    private boolean addTo;
    private LitterListAdapter mLitterListAdapter;

    public UpdateCollectionCount(LitterPiece lp, String volID, String teamID, String eventID,
                                 boolean addTo, LitterListAdapter mLitterListAdapter) {
        this.litterPiece = lp;
        this.volID = volID;
        this.teamID = teamID;
        this.eventID = eventID;
        this.addTo = addTo;
        this.mLitterListAdapter = mLitterListAdapter;
    }

    protected Boolean doInBackground(Void... params) {

        HttpURLConnection urlConnection = null;

        try {

            URL url = new URL(CREATE_USER_BASE);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("Content-Type", "application/json;");
            urlConnection.setRequestMethod("POST");
            urlConnection.connect();

            long count = litterPiece.getCount();
            count = (addTo) ? ++count : --count;

            if(count >= 0){
                litterPiece.setCount(count);
                //Create JSONObject here
                JSONObject jsonParam = new JSONObject();
                jsonParam.put(LITTER_ID_TAG, litterPiece.getLitterId() + "");
                jsonParam.put(VOL_ID_TAG, volID);
                jsonParam.put(TEAM_ID_TAG, teamID);
                jsonParam.put(EVENT_ID_TAG, eventID);
                jsonParam.put(TALLY_TAG, count + "");

                // push
                OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
                wr.write(jsonParam.toString());
                wr.flush();
            }

            int httpResult = urlConnection.getResponseCode();
            if(httpResult == HttpURLConnection.HTTP_OK){
                return true;
            }

        } catch (IOException e) {

            Log.i(LOG_TAG, "connection failed");

        } catch (JSONException e) {

            e.printStackTrace();

        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

        }

        return false;
    }

    protected void onPostExecute(Boolean success) {

        if(success)
            mLitterListAdapter.notifyDataSetChanged();

    }


}
