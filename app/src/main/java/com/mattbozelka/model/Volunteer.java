package com.mattbozelka.model;

/**
 *
 * Volunteer - Model Object
 *
 * Used to represent a Volunteer in the UI and communicate back to the server
 *
 * Data elements:
 * int volID - volunteer id used in database and to store locally if logged in
 * String fName - first name of volunteer
 * String lName - last name of volunteer
 * String emailAddress - email address of the volunteer
 * String password - ***( should be removed in future iterations for security )***
 *
 * Default constructor creates a blank volunteer with default values
 *
 * Non-default constructor - sets all data elements to values supplied
 *
 * includes getters and setters for all elements
 *
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
