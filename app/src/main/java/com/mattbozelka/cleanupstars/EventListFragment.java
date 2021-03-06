package com.mattbozelka.cleanupstars;

/*
*
* EventListFragment - UI Fragment
*
* Fragment loads the Event List UI and handle the logic for Event List
*
* Associated XML - fragment_event_list.xml
*
* */

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mattbozelka.AsyncTasks.EventListGetTask;
import com.mattbozelka.com.mattbozelka.custom.adapters.EventListAdapter;
import com.mattbozelka.model.Event;

import java.util.ArrayList;


public class EventListFragment extends Fragment {

    private final String LOG_TAG = EventListFragment.class.getSimpleName();
    private EventListAdapter mEventListAdapter;
    private ArrayList<Event> events = new ArrayList<Event>();

    public EventListFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_event_list, container, false);

        mEventListAdapter = new EventListAdapter(
                getActivity(),
                R.layout.event_item,
                events);

        ListView eventListView = (ListView) rootView.findViewById(R.id.event_list_view);
        eventListView.setAdapter(mEventListAdapter);

        eventListView.setOnItemClickListener(new LaunchEventAction());

        return rootView;
    }


    @Override
    public void onStart() {
        super.onStart();

        getEvents();

    }


    private void getEvents() {

        // fetch the events from the API
        EventListGetTask getEventsTask = new EventListGetTask(mEventListAdapter);
        getEventsTask.execute(); //calls do in background

    }

    private class LaunchEventAction implements AdapterView.OnItemClickListener{

        private Activity activity = getActivity();

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            SharedPreferences sharedPref = activity
                    .getSharedPreferences(getString(R.string.store_user_tag), Context.MODE_PRIVATE);
            int defaultUser = Integer.parseInt(getString(R.string.saved_user_default));
            int userLoggedIn = sharedPref.getInt(getString(R.string.store_user_tag), defaultUser);
            int eventId = events.get(position).getEventId();

            if (userLoggedIn != -1) {
                Intent intent = new Intent(activity, EventsCollectionsActivity.class);
                intent.putExtra("EventId", eventId);
                activity.startActivity(intent);
            }

        }

    }

}
