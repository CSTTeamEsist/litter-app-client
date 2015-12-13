package com.mattbozelka.cleanupstars;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mattbozelka.AsyncTasks.GetEventCollections;
import com.mattbozelka.AsyncTasks.UpdateCollectionCount;
import com.mattbozelka.callbacks.GetEventId;
import com.mattbozelka.com.mattbozelka.custom.adapters.LitterListAdapter;
import com.mattbozelka.model.LitterPiece;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class EventCollectionsListFragment extends Fragment {

    private final String LOG_TAG = EventCollectionsListFragment.class.getSimpleName();
    private SharedPreferences sharedPref;
    private LitterListAdapter mLitterListAdapter;
    private ArrayList<LitterPiece> litterItems = new ArrayList<LitterPiece>();
    private int eventId;
    private int userLoggedIn;

    public EventCollectionsListFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_event_collections_list, container, false);

        litterItems.clear();
        eventId = ((GetEventId) getActivity()).getEventId();

        mLitterListAdapter = new LitterListAdapter(
                getActivity(),
                R.layout.litter_item,
                litterItems);


        ListView litterGridView = (ListView) rootView.findViewById(R.id.event_collections_list);
        litterGridView.setAdapter(mLitterListAdapter);

        litterGridView.setOnItemClickListener(new ItemAdded());
        litterGridView.setOnItemLongClickListener(new ItemRemoved());

        sharedPref = getActivity()
                .getSharedPreferences(getString(R.string.store_user_tag), Context.MODE_PRIVATE);
        int defaultUser = Integer.parseInt(getString(R.string.saved_user_default));
        userLoggedIn = sharedPref.getInt(getString(R.string.store_user_tag), defaultUser);

        GetEventCollections getEventCollectionsTask = new GetEventCollections(
                userLoggedIn,
                eventId,
                mLitterListAdapter
        );
        getEventCollectionsTask.execute();

        return rootView;
    }

    private class ItemAdded implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            LitterPiece litterPiece = litterItems.get(position);

            updateCount(
                    litterPiece,
                    userLoggedIn + "",
                    eventId + "",
                    true
            );

        }
    }

    private class ItemRemoved implements AdapterView.OnItemLongClickListener{


        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

            LitterPiece litterPiece = litterItems.get(position);

            updateCount(
                    litterPiece,
                    userLoggedIn + "",
                    eventId + "",
                    false
            );
            return true;
        }

    }

    private void updateCount(LitterPiece litterPiece, String volunteer, String event, boolean addTo){

        UpdateCollectionCount update = new UpdateCollectionCount(
                litterPiece,
                volunteer,
                "1",
                event,
                addTo,
                mLitterListAdapter
        );
        update.execute();
    }
}
