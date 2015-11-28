package com.mattbozelka.cleanupstars;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity implements LoginFragment.Callback{

    private final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String LOGINFRAGMENT_TAG = "LOGINTAG";
    private FragmentManager fragmentManager = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentManager.beginTransaction()
                .add(R.id.view_holder, new LoginFragment(), LOGINFRAGMENT_TAG)
                .commit();

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
    public void loadUI(int loadById) {

        switch (loadById){
            case 0:
                // loading the create an account fragment
                fragmentManager.beginTransaction()
                        .remove(fragmentManager.findFragmentByTag(LOGINFRAGMENT_TAG))
                        .add(R.id.view_holder, new LoginFragment())
                        .commit();
                break;
            case 1:
                // loading the create an account fragment
                fragmentManager.beginTransaction()
                        .remove(fragmentManager.findFragmentByTag(LOGINFRAGMENT_TAG))
                        .add(R.id.view_holder, new CreateAccountFragment())
                        .commit();
                break;
            case 2:
                fragmentManager.beginTransaction()
                        .remove(fragmentManager.findFragmentByTag(LOGINFRAGMENT_TAG))
                        .add(R.id.view_holder, new UserHomeFragment())
                        .commit();
                break;
        };

    }
}
