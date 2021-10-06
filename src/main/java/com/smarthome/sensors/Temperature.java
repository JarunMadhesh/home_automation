package com.smarthome.sensors;

public class Temperature {
    private long temperature;
    private String roomName;

    public Temperature(String name) {
        this.temperature = 25;
        roomName = name;
    }

    public void setTemperature(long temperature) {
        this.temperature = temperature;
        System.out.println("\tTemperature in "+ roomName+ " is set to "+ temperature + " *C.");
    }

    public long getTemperature() {
        return temperature;
    }

}
