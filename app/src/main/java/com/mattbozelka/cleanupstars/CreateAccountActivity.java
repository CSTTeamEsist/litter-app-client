package com.mattbozelka.cleanupstars;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.mattbozelka.model.LaunchFragmentsContract;

/**
 * Created by Captain on 12/12/15.
 */
public class CreateAccountActivity extends AppCompatActivity{

    private final String LOG_TAG = CreateAccountActivity.class.getSimpleName();
    private FragmentManager fragmentManager = getFragmentManager();
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentManager.beginTransaction()
                .add(R.id.view_holder,
                        new CreateAccountFragment(),
                        LaunchFragmentsContract.CREATE_ACCOUNT_FRAGMENT_TAG)
                .commit();

    }

}