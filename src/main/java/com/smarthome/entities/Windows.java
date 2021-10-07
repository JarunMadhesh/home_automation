package com.smarthome.entities;

import org.json.simple.JSONObject;

public class Windows {
    private Long id;
    private String roomName;
    private boolean isOpen;

    public Windows(JSONObject obj, String name) {
        id = (Long) obj.get("id");
        roomName = name;
        isOpen = false;
    }

    public void open() {
        if(!isOpen) {
            isOpen = true;
            System.out.println("\tWindow " +id+ " in "+ roomName+ " is opened.");
        }
    }

    public void close() {
        if(isOpen) {
            isOpen = false;
            System.out.println("\tWindow " +id+ " in "+ roomName+ " is closed.");
        }
    }

    public boolean isOn() {
        return isOpen;
    }
}
