package com.smarthome.sensors;

public class Activity {

    private boolean isPersonPresent;
    private final String roomName;

    public Activity(String name) {
        isPersonPresent = false;
        roomName = name;
    }

    public void setPersonPresent(boolean isPersonPresent) {
        this.isPersonPresent = isPersonPresent;
        System.out.println("\tActivity in "+ roomName+ " is set to "+ isPersonPresent + ".");
    }

    public boolean isPersonPresent() {
        return isPersonPresent;
    }

}
