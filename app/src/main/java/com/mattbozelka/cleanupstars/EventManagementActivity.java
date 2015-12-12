package com.mattbozelka.cleanupstars;


import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mattbozelka.model.LaunchFragmentsContract;

public class EventManagementActivity extends AppCompatActivity {
    private final String LOG_TAG = EventManagementActivity.class.getSimpleName();
    private FragmentManager fragmentManager = getFragmentManager();
    private final String STORED_FRAG = "storeFrag";
    private Toolbar toolbar;
    private int currentFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_management);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(savedInstanceState != null){
            currentFrag = savedInstanceState.getInt(STORED_FRAG);
        }else{
            currentFrag = LaunchFragmentsContract.EVENT_ID;
        }

        loadUI(currentFrag);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle outState){

        outState.putInt(STORED_FRAG, currentFrag);
        super.onSaveInstanceState(outState);
    }

    public void loadUI(int loadById) {

        switch (loadById){
            case LaunchFragmentsContract.EVENT_LIST_ID:
                // loading the event lists frag
                loadFragment(R.id.event_management_fragment_holder,
                        new EventListFragment(),
                        LaunchFragmentsContract.EVENT_LIST_ID,
                        LaunchFragmentsContract.EVENT_LIST_TAG);
                break;
            case LaunchFragmentsContract.EVENT_ID:
                // loading frag of event
                loadFragment(R.id.event_management_fragment_holder,
                        new EventCollectionsListFragment(),
                        LaunchFragmentsContract.EVENT_ID,
                        LaunchFragmentsContract.EVENT_TAG);
                break;
        };

    }

    private void loadFragment(int resource, Fragment createFrag, int fragId, String fragTg){
        Fragment checkCurrfrag = fragmentManager.findFragmentByTag(fragTg);

        if(checkCurrfrag == null){
            currentFrag = fragId;
            fragmentManager.beginTransaction()
                    .add(resource, createFrag, fragTg)
                    .commit();
        }
    }

    public void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    public void setActionBarSubTitle(String title){
        getSupportActionBar().setSubtitle(title);
    }

}
