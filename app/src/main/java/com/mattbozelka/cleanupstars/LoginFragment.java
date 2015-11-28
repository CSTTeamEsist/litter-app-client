package com.mattbozelka.cleanupstars;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class LoginFragment extends Fragment{

    private final String LOG_TAG = LoginFragment.class.getSimpleName();
    private Context context;

    public LoginFragment() {}

    /**
     * CALL BACK to be used by the main activity so it can unload and load
     * the user fragment once log in has been successful
     */
    public interface Callback {
        public void loadUI(int loadById);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_login, container, false);
        context = getActivity();

        Button loginBtn = (Button) root.findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new LoginAction());

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


    private class LoginAction implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            ((Callback) context).loadUI(0);
        }

    }
}
