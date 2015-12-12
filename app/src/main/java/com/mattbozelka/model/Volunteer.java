package com.mattbozelka.model;

/**
 * Created by Julie on 12/5/2015.
 */


public class Volunteer {

    private int volID;
    private String fName;
    private String lName;
    private String emailAddress;
    private String password;

    public Volunteer() {
        this.volID = -1;
        this.fName = null;
        this.lName = null;
        this.emailAddress = null;
        this.password = null;
    }

    public Volunteer(int volID, String fName, String lName, String emailAddress, String password) {
        this.volID = volID;
        this.fName = fName;
        this.lName = lName;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public int getVolID() {
        return volID;
    }

    public void setVolID(int volID) {
        this.volID = volID;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
