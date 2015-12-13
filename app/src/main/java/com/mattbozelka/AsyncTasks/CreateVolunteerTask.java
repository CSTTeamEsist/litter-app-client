package com.mattbozelka.AsyncTasks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.mattbozelka.cleanupstars.R;
import com.mattbozelka.cleanupstars.UserHomeActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Captain on 12/12/15.
 */
public class CreateVolunteerTask extends AsyncTask<Void, Void, Integer> {

    private final String LOG_TAG = CreateVolunteerTask.class.getSimpleName();
    private final String FNAME_TAG = "FName";
    private final String LNAME_TAG = "LName";
    private final String EMAIL_TAG = "emailAddress";
    private final String PASSWORD_TAG = "password";
    private String CREATE_USER_BASE ="http://cstserver2a.bitnamiapp.com/litter-service-webapp/webapi/" +
            "volunteer-list/create/";
    private Activity activity;
    private int userID;

    String fname;
    String lname;
    String email;
    String password;

    public CreateVolunteerTask(String fname, String lname, String email, String password,
                               Activity activity) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.activity = activity;
    }

    protected Integer doInBackground(Void... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String volunteerJsonStr = null;


        try {

            URL url = new URL(CREATE_USER_BASE);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("Content-Type", "application/json;");
            urlConnection.setRequestMethod("POST");
            urlConnection.connect();

            //Create JSONObject here
            JSONObject jsonParam = new JSONObject();
            jsonParam.put(FNAME_TAG, fname);
            jsonParam.put(LNAME_TAG, lname);
            jsonParam.put(EMAIL_TAG, email);
            jsonParam.put(PASSWORD_TAG, password);

            // push
            OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
            wr.write(jsonParam.toString());
            wr.flush();

            int httpResult = urlConnection.getResponseCode();
            if(httpResult == HttpURLConnection.HTTP_OK){

                StringBuffer buffer = new StringBuffer();
                reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    Log.i(LOG_TAG, "buffering was null");
                }

                volunteerJsonStr = buffer.toString();

            }else{
                Log.i(LOG_TAG, urlConnection.getResponseMessage());
            }

        } catch (IOException e) {

            Log.i(LOG_TAG, "connection failed");

        } catch (JSONException e) {

            e.printStackTrace();

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

            return extractData(volunteerJsonStr);

        }catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();

        }

        return -1;
    }

    protected void onPostExecute(Integer id) {

        if(id == -1){
            return;
        }

        userID = id;
        setUserAsLoggedIn();
        Intent intent = new Intent(activity, UserHomeActivity.class);
        activity.startActivity(intent);

    }

    private void setUserAsLoggedIn(){
        String userTag = activity.getResources().getString(R.string.store_user_tag);
        SharedPreferences sharedPref = activity.getSharedPreferences(
                activity.getString(R.string.store_user_tag),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(userTag, userID);
        editor.commit();
    }

    private  Integer extractData (String volunteerJsonStr) throws JSONException {

        final String VOL_ID = "volID";

        JSONObject user = new JSONObject(volunteerJsonStr);
        String userid = user.getString(VOL_ID);

        if(!TextUtils.isEmpty(userid) && userid != null){
            return Integer.parseInt(userid);
        }

        return -1;
    }
}