package com.mattbozelka.AsyncTasks;

import android.os.AsyncTask;
import android.util.Log;

import com.mattbozelka.com.mattbozelka.custom.adapters.EventListAdapter;
import com.mattbozelka.model.Event;

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


public class EventListGetTask extends AsyncTask<Void, Void, ArrayList<Event>> {

    private final String LOG_TAG = EventListGetTask.class.getSimpleName();
    private final String COLLECTION_API_BASE = "http://cstserver2a.bitnamiapp.com/litter-service-webapp/webapi/event-list/";
    private EventListAdapter events;

    public EventListGetTask(EventListAdapter events){
        this.events = events;
    }

    @Override
    protected ArrayList<Event> doInBackground(Void... params) {

        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String eventListJsonStr = null;

        try {

            URL url = new URL(COLLECTION_API_BASE);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            Log.i(LOG_TAG, "connected");

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();


            if (inputStream == null) {
                System.out.println("system string was null");
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                System.out.println("buffering was null");
            }

            eventListJsonStr = buffer.toString();

        } catch (IOException e) {

            Log.e(LOG_TAG, "Error ", e);
            return null;

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

            return extractData(eventListJsonStr);

        } catch (JSONException e) {

            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();

        }

        return null;
    }

    private ArrayList<Event> extractData(String eventListJsonStr) throws JSONException {

        ArrayList<Event> dataEvents = new ArrayList<Event>();

        // Items to extract
        final String EVENT_DATE = "eventDate";
        final String EVENT_ID = "eventID";
        final String EVENT_LOCATION = "location";

        JSONArray eventsArray = new JSONArray(eventListJsonStr);
        int eventsLength =  eventsArray.length();

        for(int i = 0; i < eventsLength; ++i) {

            JSONObject event = eventsArray.getJSONObject(i);

            String date = event.getString(EVENT_DATE);
            String location = event.getString(EVENT_LOCATION);
            int id = event.getInt(EVENT_ID);

            Event ev = new Event(date, location, id);
            dataEvents.add(ev);

        }

        return dataEvents;

    }


    @Override
    protected void onPostExecute(ArrayList<Event> results) {

        if (results != null && events != null) {

            events.clear();
            for(Event event : results) {
                events.add(event);
            }

        }

    }

}
