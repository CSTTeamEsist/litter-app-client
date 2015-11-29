package com.mattbozelka.cleanupstars;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mattbozelka.callbacks.MainActivityCallback;
import com.mattbozelka.model.LaunchFragmentsContract;


public class MainActivity extends AppCompatActivity implements MainActivityCallback{

    private final String LOG_TAG = MainActivity.class.getSimpleName();
    private FragmentManager fragmentManager = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadUI(LaunchFragmentsContract.LOGIN_SCREEN_ID);

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
            case LaunchFragmentsContract.LOGIN_SCREEN_ID:
                // loading the log in frag
                fragmentManager.beginTransaction()
                        .replace(R.id.view_holder, new LoginFragment(),
                                LaunchFragmentsContract.LOGIN_FRAGMENT_TAG)
                        .commit();
                break;
            case LaunchFragmentsContract.CREATE_ACCOUNT_SCREEN_ID:
                // loading the create an account fragment
                fragmentManager.beginTransaction()
                        .replace(R.id.view_holder, new CreateAccountFragment(),
                                LaunchFragmentsContract.CREATE_ACCOUNT_FRAGMENT_TAG)
                        .commit();
                break;
            case LaunchFragmentsContract.USER_HOME_SCREEN_ID:
                // loading the User Home Frag
                fragmentManager.beginTransaction()
                        .replace(R.id.view_holder, new UserHomeFragment(),
                                LaunchFragmentsContract.USER_HOME_FRAGMENT_TAG)
                        .commit();
        };

    }
}
