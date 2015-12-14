package com.mattbozelka.cleanupstars;

/*
*
* EventsCollectionsActivity - UI Activity
*
* Activity that handles and loads the Event Collections List
*
* Loads a EventCollectionsListFragment
*
* Associated XML - activity_events_collection.xml
*
* */


import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.mattbozelka.callbacks.GetEventId;
import com.mattbozelka.model.LaunchFragmentsContract;

public class EventsCollectionsActivity extends AppCompatActivity implements GetEventId {

    private final String LOG_TAG = EventsCollectionsActivity.class.getSimpleName();
    private FragmentManager fragmentManager = getFragmentManager();
    private SharedPreferences sharedPref;
    private Toolbar toolbar;
    private int eventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_collection);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentManager.beginTransaction()
                .add(R.id.view_holder_event_collections,
                        new EventCollectionsListFragment(),
                        LaunchFragmentsContract.EVENT_TAG)
                .commit();

        Intent intent = getIntent();
        eventId = intent.getIntExtra("EventId", -1);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.sign_out_settings) {
            int defaultUser = Integer.parseInt(getString(R.string.saved_user_default));
            String userTag = this.getResources().getString(R.string.store_user_tag);

            SharedPreferences sharedPref = getSharedPreferences(
                    this.getString(R.string.store_user_tag),
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(userTag, defaultUser);
            editor.commit();

            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public int getEventId() {
        return eventId;
    }
}

