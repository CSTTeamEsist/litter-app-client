package com.mattbozelka.cleanupstars;

/*
*
* CreateAccountFragment - UI Fragment
*
* Fragment that handles all the UI logic for a user to create an account
*
* Associated XML - fragment_create_account.xml
*
* */

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mattbozelka.AsyncTasks.CreateVolunteerTask;

public class CreateAccountFragment extends Fragment{

    private final String LOG_TAG = CreateAccountFragment.class.getSimpleName();
    private Context context;
    private static final String FNAME = "userFname";
    private static final String LNAME = "puserLname";
    private static final String EMAIL = "userEmail";
    private static final String PASSWORD = "userPw";
    static final String ERROR = "formError";
    private EditText fname;
    private EditText lname;
    private EditText password;
    private EditText email;
    private TextView errorMessage;

    public CreateAccountFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_create_account, container, false);

        fname = (EditText) root.findViewById(R.id.createFirstName);
        lname = (EditText) root.findViewById(R.id.createLastName);
        password = (EditText) root.findViewById(R.id.loginTextPassword);
        email = (EditText) root.findViewById(R.id.loginTextEmail);
        errorMessage = (TextView) root.findViewById(R.id.error_tv);

        Button createAccountBtn = (Button) root.findViewById(R.id.submit_create_account_btn);
        createAccountBtn.setOnClickListener(new CreateAccountAction());

        return root;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        if(savedInstanceState != null){
            fname.setText(savedInstanceState.getString(FNAME));
            lname.setText(savedInstanceState.getString(LNAME));
            email.setText(savedInstanceState.getString(EMAIL));
            password.setText(savedInstanceState.getString(PASSWORD));
            if(savedInstanceState.getInt(ERROR) == View.VISIBLE)
                errorMessage.setVisibility(View.VISIBLE);
        }

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putString(FNAME, fname.getText().toString());
        outState.putString(LNAME, lname.getText().toString());
        outState.putString(PASSWORD, password.getText().toString());
        outState.putString(EMAIL, email.getText().toString());

        super.onSaveInstanceState(outState);
    }


    private class CreateAccountAction implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            String fnameInput = fname.getText().toString();
            String lnameInput = lname.getText().toString();
            String pwInput = password.getText().toString();
            String emInput = email.getText().toString();


            if(isNotEmptyInput(fnameInput) &&
                    isNotEmptyInput(lnameInput) &&
                    isNotEmptyInput(pwInput) &&
                    isValidEmail(emInput)){

                errorMessage.setVisibility(View.INVISIBLE);
                CreateVolunteerTask createVolunteerTask = new CreateVolunteerTask(
                        fnameInput,
                        lnameInput,
                        emInput,
                        pwInput,
                        getActivity()
                );
                createVolunteerTask.execute();

            }else{

                errorMessage.setVisibility(View.VISIBLE);
            }

        }

    }

    private boolean isNotEmptyInput(String input){

        return (TextUtils.isEmpty(input)) ? false : true;

    }

    private boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target))
            return false;
        else
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

}
