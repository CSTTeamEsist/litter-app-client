package com.mattbozelka.AsyncTasks;

/*
*
* GetVolunteerTask
*
* Background Task
*
* Uses a RESTful GET connection to the server to receive a JSON object of a specific User. Used
* within the login screen. Sends a password and an email address in the get request, and if the
* information matches a DB item then that item in the DB is returned back in the response.
*
* Constructor expects:
* Volunteer currentUser - a volunteer object that contains the entered password and email by
*   a user trying to log in
* int userID - an integer that will hold the ID of the user on a successful query,
* TextView errorView - UI text view to display any error messages,
* Activity activity - the calling activity
*
* */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.mattbozelka.cleanupstars.R;
import com.mattbozelka.cleanupstars.UserHomeActivity;
import com.mattbozelka.model.Volunteer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetVolunteerTask extends AsyncTask<Void, Void, Integer>{

    private final String LOG_TAG = GetVolunteerTask.class.getSimpleName();
    private String loginUrl ="http://cstserver2a.bitnamiapp.com/" +
            "litter-service-webapp/webapi/login/";
    private String userEmail;
    private String password;
    private TextView errorView;
    private int userID;
    private Activity activity;

    public GetVolunteerTask(Volunteer currentUser,
                            int userID,
                            TextView errorView,
                            Activity activity) {
        this.userEmail = currentUser.getEmailAddress();
        this.password = currentUser.getPassword();
        this.userID = userID;
        this.errorView = errorView;
        this.activity = activity;
    }

    protected Integer doInBackground(Void... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String volunteerJsonStr = null;

        if(TextUtils.isEmpty(userEmail) &&
                TextUtils.isEmpty(password)){
            return -1;
        }

        try {
            loginUrl += userEmail + "/" + password;

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

            volunteerJsonStr = buffer.toString();

        } catch (IOException e) {

            Log.i(LOG_TAG, "connection failed");
            return -1;

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
            errorView.setVisibility(View.VISIBLE);
            return;
        }

        userID = id;
        errorView.setVisibility(View.INVISIBLE);
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

        final String VOL_ID = "userID";

        JSONObject user = new JSONObject(volunteerJsonStr);
        String userid = user.getString(VOL_ID);

        if(!TextUtils.isEmpty(userid)){
            return Integer.parseInt(userid);
        }

        return -1;
    }
}
