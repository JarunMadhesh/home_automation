package com.smarthome.entities;

import org.json.simple.JSONObject;

public class Door {
    private final Long id;
    private final String roomName;
    private boolean isOpen;

    public Door(JSONObject obj, String name) {
        id = (Long) obj.get("id");
        roomName = name;
        isOpen = true;
    }

    public void open() {
        if(!isOpen) {
            isOpen = true;
            System.out.println("\tDoor " +id+ " in "+ roomName+ " is opened.");
        }
    }

    public void close() {
        if(isOpen) {
            isOpen = false;
            System.out.println("\tDoor " +id+ " in "+ roomName+ " is closed.");
        }
    }
    public boolean isOn() {
        return isOpen;
    }
}
