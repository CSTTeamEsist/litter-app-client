package com.mattbozelka.AsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import com.mattbozelka.callbacks.MainActivityCallback;
import com.mattbozelka.model.LaunchFragmentsContract;
import com.mattbozelka.model.Volunteer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Julie on 12/5/2015.
 */
public class GetVolunteerTask extends AsyncTask<Volunteer, Void, Volunteer{

    private final String LOG_TAG = GetVolunteerTask.class.getSimpleName();
    private final String COLLECTION_API_BASE="http://cstserver2a." +
            "bitnamiapp.com/litter-service-webapp/webapi/volunteer";
    private String username;
    private String password;
  //  private Volunteer currentUser;

    public GetVolunteerTask(Volunteer currentUser) {
        this.username = currentUser.getEmailAddress();
        this.password = currentUser.getPassword();
    }

    protected Volunteer doInBackground(Volunteer... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        String volunteerJsonStr = null;
        Volunteer volunteer;
        StringBuilder buffer = new StringBuilder();
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL(COLLECTION_API_BASE);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.connect();

            // Send post request
            urlConnection.setDoOutput(true);
            DataOutputStream userInfo = new DataOutputStream(urlConnection.getOutputStream());
            //userInfo.writeBytes( ????????);
            userInfo.flush();
            userInfo.close();


            //get data
            in = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                buffer.append(inputLine + "\n");
            }

            if (buffer.length() == 0) {
                System.out.println("buffering was null");
            }

            volunteerJsonStr = buffer.toString();

        } catch (IOException e) {
            return null;


        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (final IOException e) {
                }
            }

        }

        try {

             return extractData(volunteerJsonStr);

        }catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();

        }

        return null;
    }

    protected void onPostExecute(int userID) {

    }

    private  Volunteer extractData (String volunteerJsonStr) throws JSONException {
        Volunteer currentUser = null;
        final String VOL_ID="volID";
        final String FNAME = "firstName";
        final String LNAME = "lastName";
        final String EMAIL = "email";
        final String PASSWORD = "password";

        JSONObject JSONUser = new JSONObject(volunteerJsonStr);
        currentUser.setVolID(JSONUser.getInt(VOL_ID));
        currentUser.setFName(JSONUser.getString(FNAME));
        currentUser.setLName(JSONUser.getString(LNAME));

        return currentUser;
    }
}
