package com.mattbozelka.cleanupstars;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mattbozelka.com.mattbozelka.custom.adapters.LitterListAdapter;
import com.mattbozelka.model.LitterPiece;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class EventCollectionsListFragment extends Fragment {

    private final String LOG_TAG = EventCollectionsListFragment.class.getSimpleName();
    private LitterListAdapter mLitterListAdapter;
    private ArrayList<LitterPiece> litterItems = new ArrayList<LitterPiece>();

    public EventCollectionsListFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        litterItems.clear();
        LitterPiece l1 = new LitterPiece("Bags (Paper)", 0, "cig_icon");
        LitterPiece l2 = new LitterPiece("Bags (Plastic)", 0, "cig_icon");
        LitterPiece l3 = new LitterPiece("Beverage Bottles (Plastic)", 0, "platic_bottle_icon");
        LitterPiece l4 = new LitterPiece("Beverage Bottles (Glass)", 0, "cig_icon");
        LitterPiece l5 = new LitterPiece("Caps, Lids", 0, "cig_icon");
        LitterPiece l6 = new LitterPiece("Cigarette / Cigarette Butts", 0, "cig_icon");
        LitterPiece l7 = new LitterPiece("Beverage Cans", 0, "cig_icon");
        LitterPiece l8 = new LitterPiece("Food Wrappers / Containers", 0, "cig_icon");
        LitterPiece l9 = new LitterPiece("Cups, Plates, Forks, Knives, Spoons", 0, "cig_icon");
        LitterPiece l10 = new LitterPiece("Straws, Stirrers", 0, "cig_icon");
        LitterPiece l11 = new LitterPiece("Misc", 0, "cig_icon");

        litterItems.add(l1);
        litterItems.add(l2);
        litterItems.add(l3);
        litterItems.add(l4);
        litterItems.add(l5);
        litterItems.add(l6);
        litterItems.add(l7);
        litterItems.add(l8);
        litterItems.add(l9);
        litterItems.add(l10);
        litterItems.add(l11);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_event_collections_list, container, false);
        ((EventManagementActivity) getActivity()).setActionBarTitle("Event Name");
        ((EventManagementActivity) getActivity()).setActionBarSubTitle("Check List");

        mLitterListAdapter = new LitterListAdapter(
                getActivity(),
                R.layout.litter_item,
                litterItems);


        ListView litterGridView = (ListView) rootView.findViewById(R.id.event_collections_list);
        litterGridView.setAdapter(mLitterListAdapter);

        litterGridView.setOnItemClickListener(new ItemAdded());
        litterGridView.setOnItemLongClickListener(new ItemRemoved());

        return rootView;
    }

    private class ItemAdded implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            long count;
            LitterPiece litterPiece = litterItems.get(position);
            count = litterPiece.getCount();
            count++;
            litterPiece.setCount(count);
            mLitterListAdapter.notifyDataSetChanged();
        }
    }

    private class ItemRemoved implements AdapterView.OnItemLongClickListener{


        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

            long count;
            LitterPiece litterPiece = litterItems.get(position);
            count = litterPiece.getCount();

            if(count == 0)
                return true;

            count--;
            litterPiece.setCount(count);
            mLitterListAdapter.notifyDataSetChanged();
            return true;
        }

    }
}
