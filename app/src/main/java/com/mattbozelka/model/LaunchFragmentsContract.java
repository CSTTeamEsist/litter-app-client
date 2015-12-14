package com.mattbozelka.model;

/*
*
* LaunchFragmentsContract
*
* class of entirely static strings used in the activities to register Fragments
* with the Android stack
*
* Purely used as a repository of string names used between activities in order to cut down
* on duplications and mistakes if misspelled fragment tag names.
*
* */

public class LaunchFragmentsContract {

    public static final String LOGIN_FRAGMENT_TAG = "LOGINTAG";
    public static final String CREATE_ACCOUNT_FRAGMENT_TAG = "CREATETAG";
    public static final String USER_HOME_FRAGMENT_TAG = "USERTAG";
    public static final String EVENT_LIST_TAG = "EVENTLIST";
    public static final String EVENT_TAG = "EVENT";
}
