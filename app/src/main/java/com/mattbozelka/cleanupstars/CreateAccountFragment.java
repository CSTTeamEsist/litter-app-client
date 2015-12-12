package com.mattbozelka.cleanupstars;

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

public class CreateAccountFragment extends Fragment{

    private final String LOG_TAG = CreateAccountFragment.class.getSimpleName();
    private Context context;
    static final String FNAME = "userFname";
    static final String LNAME = "puserLname";
    static final String EMAIL = "userEmail";
    static final String PASSWORD = "userPw";
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
        ((MainActivity) getActivity()).setActionBarTitle("Create Account");

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
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        if(savedInstanceState != null){
            fname.setText(savedInstanceState.getString(FNAME));
            lname.setText(savedInstanceState.getString(LNAME));
            email.setText(savedInstanceState.getString(EMAIL));
            password.setText(savedInstanceState.getString(PASSWORD));
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

            String fnameInput = fname.getText().toString();
            String lnameInput = lname.getText().toString();
            String pwInput = password.getText().toString();
            String emInput = email.getText().toString();


            if(isNotEmptyInput(fnameInput) &&
                    isNotEmptyInput(lnameInput) &&
                    isNotEmptyInput(pwInput) &&
                    isValidEmail(emInput)){

                errorMessage.setVisibility(View.INVISIBLE);
                // info is all there and email is valid
                // so call out to server to add user

                //            ((MainActivityCallback) getActivity())
//                    .loadUI(LaunchFragmentsContract.USER_HOME_SCREEN_ID);

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
