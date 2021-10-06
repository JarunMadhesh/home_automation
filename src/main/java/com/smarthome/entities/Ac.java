package com.smarthome.entities;

import org.json.simple.JSONObject;

public class Ac {
    private Long id;
    private boolean isOn;
    private final String roomName;

    public Ac(JSONObject obj, String name) {
        id = (Long) obj.get("id");
        roomName = name;
        isOn = false;
    }

    public void turnOn() {
        if(!isOn) {
            isOn = true;
            System.out.println("\t" + "AC in "+ roomName+ " is turned off.");
        }
    }

    public void turnOff() {
        if(isOn) {
            isOn = false;
            System.out.println("\t" + "AC in " + roomName + " is turned off.");
        }
    }

    public boolean isOn() {
        return isOn;
    }
}
