package com.mattbozelka.cleanupstars;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mattbozelka.AsyncTasks.GetVolunteerTask;
import com.mattbozelka.model.Volunteer;

public class LoginFragment extends Fragment {

    private final String LOG_TAG = LoginFragment.class.getSimpleName();
    private static final String USERID = "userID";
    static final String EMAIL = "userEmail";
    static final String PASSWORD = "userPw";
    static final String ERROR = "formError";
    private View root;
    private Button loginBtn;
    private Button createAccount;
    private EditText userEmail;
    private EditText password;
    private TextView errorMessage;
    private int userId = -1;
    private Volunteer currentUser;

    public LoginFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_login, container, false);

        loginBtn = (Button) root.findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new LoginAction());

        createAccount = (Button) root.findViewById(R.id.create_account_btn);
        createAccount.setOnClickListener(new CreateAccountAction());

        userEmail = (EditText) root.findViewById(R.id.loginTextEmail);
        password = (EditText) root.findViewById(R.id.loginTextPassword);
        errorMessage = (TextView) root.findViewById(R.id.errorMessage);

        currentUser = new Volunteer();

        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        if(savedInstanceState != null){
            userEmail.setText(savedInstanceState.getString(EMAIL));
            password.setText(savedInstanceState.getString(PASSWORD));
            if(savedInstanceState.getInt(ERROR) == View.VISIBLE)
                errorMessage.setVisibility(View.VISIBLE);
        }

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putString(PASSWORD, password.getText().toString());
        outState.putString(EMAIL, userEmail.getText().toString());
        outState.putInt(ERROR, errorMessage.getVisibility());

        super.onSaveInstanceState(outState);
    }


    private void getUserID() {

        GetVolunteerTask getVolunteerTask = new GetVolunteerTask(
                currentUser,
                userId,
                errorMessage,
                getActivity()
        );
        getVolunteerTask.execute();

    }

    public class LoginAction implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            currentUser.setEmailAddress(userEmail.getText().toString());
            currentUser.setPassword(password.getText().toString());
            //verify user registration
            getUserID();
        }

    }

    private class CreateAccountAction implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), CreateAccountActivity.class);
            startActivity(intent);
        }

    }

}
