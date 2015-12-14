package com.mattbozelka.model;

/*
*
* LitterPiece - Model Object
*
* LitterPiece represents a piece of litter that can be collected at an event.
*
* Data elements include:
* String name - name of the LitterPiece
* int litterId - id of the LitterPiece in database
* long count - Current count of pieces collected by user
* String iconName - name of the icon to be used in the UI
*
* Default Constructor sets all data elements to elements type default
*
* Non-default Constructor sets all elements to the supplied values
*
* includes getters and setters for all data elements
*
* */

public class LitterPiece {

    private String name;
    private int litterId;
    private long count;
    private String iconName;

    public LitterPiece(){
        this.name = null;
        this.count = 0;
        this.iconName = null;
        this.litterId = 0;
    }

    public LitterPiece(String name, long count, String iconName, int litterId) {
        this.name = name;
        this.count = count;
        this.iconName = iconName;
        this.litterId = litterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public int getLitterId() {
        return litterId;
    }

    public void setLitterId(int litterId) {
        this.litterId = litterId;
    }

}
