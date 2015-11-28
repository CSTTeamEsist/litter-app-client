package com.mattbozelka.cleanupstars;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.mattbozelka.com.mattbozelka.custom.adapters.LitterListAdapter;
import com.mattbozelka.model.LitterPiece;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class LitterListActivityFragment extends Fragment {

    private final String LOG_TAG = LitterListActivityFragment.class.getSimpleName();
    private LitterListAdapter mLitterListAdapter;
    private ArrayList<LitterPiece> litterItems = new ArrayList<LitterPiece>();

    public LitterListActivityFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        litterItems.clear();
        LitterPiece l1 = new LitterPiece("Rubber", 0, "icon1");
        LitterPiece l2 = new LitterPiece("Glass", 0, "icon2");
        LitterPiece l3 = new LitterPiece("Plastic", 0, "icon3");
        LitterPiece l4 = new LitterPiece("Metal", 0, "icon4");
        LitterPiece l5 = new LitterPiece("Cigarette Butts", 0, "icon5");
        LitterPiece l6 = new LitterPiece("Misc", 0, "icon6");

        litterItems.add(l1);
        litterItems.add(l2);
        litterItems.add(l3);
        litterItems.add(l4);
        litterItems.add(l5);
        litterItems.add(l6);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_litter_list, container, false);

        mLitterListAdapter = new LitterListAdapter(
                getActivity(),
                R.layout.litter_item,
                litterItems);


        GridView litterGridView = (GridView) rootView.findViewById(R.id.litter_list_grid);
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
