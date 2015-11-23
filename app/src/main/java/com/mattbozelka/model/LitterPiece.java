package com.mattbozelka.model;


public class LitterPiece {

    private String name;
    private long count;
    private String iconName;

    public LitterPiece(){
        this.name = null;
        this.count = 0;
        this.iconName = null;
    }

    public LitterPiece(String name, long count, String iconName) {
        this.name = name;
        this.count = count;
        this.iconName = iconName;
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

}
