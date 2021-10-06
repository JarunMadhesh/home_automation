package com.smarthome.entities;

import org.json.simple.JSONObject;

public class WindowShades {
    private final Long id;
    private boolean isOpen;

    private final String roomName;

    public WindowShades(JSONObject obj, String name) {
        id = (Long) obj.get("id");
        roomName = name;
        isOpen = false;
    }

    public void open() {
        if(!isOpen) {
            isOpen = true;
            System.out.println("\tWindow shade " +id+ " in "+ roomName+ " is opened.");
        }
    }

    public void close() {
        if(isOpen) {
            isOpen = false;
            System.out.println("\tWindow shade " +id+ " in "+ roomName+ " is closed.");
        }
    }

    public boolean isOn() {
        return isOpen;
    }
}
