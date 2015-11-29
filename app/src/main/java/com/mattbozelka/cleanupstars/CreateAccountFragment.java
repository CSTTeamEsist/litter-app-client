package com.mattbozelka.cleanupstars;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mattbozelka.callbacks.MainActivityCallback;
import com.mattbozelka.model.LaunchFragmentsContract;

public class CreateAccountFragment extends Fragment{

    private final String LOG_TAG = CreateAccountFragment.class.getSimpleName();
    private Context context;

    public CreateAccountFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_create_account, container, false);

        Button createAccountBtn = (Button) root.findViewById(R.id.submit_create_account_btn);
        createAccountBtn.setOnClickListener(new CreateAccountAction());

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    private class CreateAccountAction implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            ((MainActivityCallback) getActivity())
                    .loadUI(LaunchFragmentsContract.USER_HOME_SCREEN_ID);
        }

    }
}
