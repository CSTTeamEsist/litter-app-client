package com.mattbozelka.cleanupstars;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mattbozelka.AsyncTasks.GetVolunteerTask;
import com.mattbozelka.callbacks.MainActivityCallback;
import com.mattbozelka.model.LaunchFragmentsContract;
import com.mattbozelka.model.Volunteer;

public class LoginFragment extends Fragment {

    private final String LOG_TAG = LoginFragment.class.getSimpleName();
    private View root;
    private Button loginBtn;
    private Button createAccount;
    private int attemptsCounter = 3;
    private EditText username;
    private EditText password;
    private TextView txt1;
    private TextView txt2;
    static final String USERID = "userID";
    private long result;
    private Volunteer currentUser = new Volunteer();

    public LoginFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(LOG_TAG, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_login, container, false);

        loginBtn = (Button) root.findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new LoginAction());

        createAccount = (Button) root.findViewById(R.id.create_account_btn);
        createAccount.setOnClickListener(new CreateAccountAction());

        username = (EditText) root.findViewById(R.id.loginTextEmail);
        password = (EditText) root.findViewById(R.id.loginTextPassword);
        txt1 = (TextView) root.findViewById(R.id.userRegistered);
        txt2 = (TextView) root.findViewById(R.id.attemptsRemaining);

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.i(LOG_TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.i(LOG_TAG, "onResume");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putLong(USERID, currentUser.getVolID());

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        if(savedInstanceState != null){
            currentUser.setVolID(savedInstanceState.getLong(USERID));
        }

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();

        Log.i(LOG_TAG, "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "onDestroy");
    }


    public class LoginAction implements View.OnClickListener {

        @Override
        public void onClick(View v) {


            currentUser.setEmailAddress(username.getText().toString());
            currentUser.setPassword(password.getText().toString());
            //verify user registration
            result = getUserID(currentUser);
            currentUser.setVolID(result);
            //notify user of failure
            if (currentUser.getVolID() == -1) {
                txt1.setText("User not registered");
                txt1.setVisibility(View.VISIBLE);
                attemptsCounter--;
                txt2.setText("Attempts remaining: " + Integer.toString(attemptsCounter));
                if (attemptsCounter == 0) {
                    loginBtn.setEnabled(false);
                    txt1.setText("Login Failed");
                    //System.exit(0);
                }
            } else {
                //set userID in userpreferences

                //load user home screen
                ((MainActivityCallback) getActivity())
                        .loadUI(LaunchFragmentsContract.USER_HOME_SCREEN_ID);
            }
        }


        public class CreateAccountAction implements View.OnClickListener {

            @Override
            public void onClick(View v) {
              /*  ((MainActivityCallback) getActivity())
                        .loadUI(LaunchFragmentsContract.CREATE_ACCOUNT_SCREEN_ID);*/
            }

        }

        private long getUserID(Volunteer currentUser) {

            GetVolunteerTask getVolunteerTask = new GetVolunteerTask(currentUser);
            getVolunteerTask.execute();
            return
        }


    }
}
