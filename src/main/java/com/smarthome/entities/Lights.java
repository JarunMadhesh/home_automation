package com.smarthome.entities;

import org.json.simple.JSONObject;

public class Lights {
    private Long id;
    private String name;
    private final String roomName;
    private boolean isOn;

    public Lights(JSONObject obj, String name) {
        id = (Long) obj.get("id");
        this.name = (String) obj.get("name");
        roomName = name;
        isOn = false;
    }

    public String getName() {
        return name;
    }

    public void turnOn() {
        if(!isOn)  {
            isOn = true;
            System.out.println("\t" + name+ " in "+ roomName+ " is turned on.");
        }
    }

    public void turnOff() {
        if(isOn) {
            isOn = false;
            System.out.println("\t" + name+ " in "+ roomName+ " is turned off.");
        }
    }

    public boolean isOn() {
        return isOn;
    }
}
