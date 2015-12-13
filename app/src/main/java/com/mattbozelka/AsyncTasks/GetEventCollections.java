package com.mattbozelka.AsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import com.mattbozelka.com.mattbozelka.custom.adapters.LitterListAdapter;
import com.mattbozelka.model.LitterPiece;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Captain on 12/12/15.
 */
public class GetEventCollections extends AsyncTask<Void, Void, ArrayList<LitterPiece>> {

    private final String LOG_TAG = GetEventCollections.class.getSimpleName();
    private String loginUrl ="http://cstserver2a.bitnamiapp.com/litter-service-webapp/webapi/" +
            "litter-collection-list/";
    private int eventID;
    private int userID;
    private LitterListAdapter adapter;

    public GetEventCollections(int userID, int eventID, LitterListAdapter adapter) {
        this.userID = userID;
        this.eventID = eventID;
        this.adapter = adapter;
    }

    protected ArrayList<LitterPiece> doInBackground(Void... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String eventCollectionsJsonStr = null;

        try {
            loginUrl += userID + "/" + eventID + "/";

            URL url = new URL(loginUrl);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            if (inputStream == null) {
                Log.i(LOG_TAG, "system string was null");
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                Log.i(LOG_TAG, "buffering was null");
            }

            eventCollectionsJsonStr = buffer.toString();

        } catch (IOException e) {

            Log.i(LOG_TAG, "connection failed");

        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }

        }

        try {

            return extractData(eventCollectionsJsonStr);

        }catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();

        }

        return null;
    }

    protected void onPostExecute(ArrayList<LitterPiece> list) {

        for(LitterPiece lp : list){
            adapter.add(lp);
        }

    }


    private ArrayList<LitterPiece> extractData (String eventCollectionsJsonStr) throws JSONException {

        //parses JsonStr to ArrayList
        ArrayList<LitterPiece> collectionArrayData = new ArrayList<LitterPiece>();

        // Items to extract
        final String TALLY = "tally";
        final String LITTER_NAME = "litterName";
        final String LITTER_ICON = "litterIcon";
        final String LITTER_ID = "litterID";

        JSONArray collectionArray = new JSONArray(eventCollectionsJsonStr);
        int eventsLength =  collectionArray.length();

        for(int i = 0; i < eventsLength; ++i) {

            JSONObject event = collectionArray.getJSONObject(i);

            String icon = event.getString(LITTER_ICON);
            String name = event.getString(LITTER_NAME);
            long tally = event.getInt(TALLY);
            int id = event.getInt(LITTER_ID);

            LitterPiece lp = new LitterPiece(name, tally, icon, id);
            collectionArrayData.add(lp);

        }

        return collectionArrayData;
    }
}
