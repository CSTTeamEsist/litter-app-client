package com.mattbozelka.cleanupstars;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.mattbozelka.model.LaunchFragmentsContract;


public class MainActivity extends AppCompatActivity{

    private final String LOG_TAG = MainActivity.class.getSimpleName();
    private FragmentManager fragmentManager = getFragmentManager();
    private SharedPreferences sharedPref;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPref = getSharedPreferences(getString(R.string.store_user_tag), Context.MODE_PRIVATE);
        int defaultUser = Integer.parseInt(getString(R.string.saved_user_default));
        int userLoggedIn = sharedPref.getInt(getString(R.string.store_user_tag), defaultUser);

        if(userLoggedIn == defaultUser){
            fragmentManager.beginTransaction()
                    .add(R.id.view_holder,
                            new LoginFragment(),
                            LaunchFragmentsContract.LOGIN_FRAGMENT_TAG)
                    .commit();
        }else{
            Intent intent = new Intent(this, UserHomeActivity.class);
            this.startActivity(intent);
        }

    }



}
