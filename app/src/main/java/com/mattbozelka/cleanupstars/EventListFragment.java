package com.mattbozelka.cleanupstars;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class EventListFragment extends Fragment {

    private final String LOG_TAG = EventListFragment.class.getSimpleName();

    public EventListFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_event_list, container, false);


        return rootView;
    }


}
