package com.mattbozelka.cleanupstars;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class UserHomeFragment extends Fragment{


    public UserHomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_user_home, container, false);

        Button eventManagementBtn = (Button) root.findViewById(R.id.event_management_btn);
        eventManagementBtn.setOnClickListener(new ViewEventsAction());

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

    private class ViewEventsAction implements View.OnClickListener{


        @Override
        public void onClick(View v) {

            // to do launch Event List Activity
            // Intent intent = new Intent(this, DisplayMessageActivity.class);

        }

    }
}
