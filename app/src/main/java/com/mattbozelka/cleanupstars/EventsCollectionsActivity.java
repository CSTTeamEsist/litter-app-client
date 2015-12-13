package com.mattbozelka.cleanupstars;

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

/**
 * Created by Captain on 12/12/15.
 */
public class EventsCollectionsActivity extends AppCompatActivity{

    private final String LOG_TAG = MainActivity.class.getSimpleName();
    private FragmentManager fragmentManager = getFragmentManager();
    private SharedPreferences sharedPref;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_collection);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

}

