package com.smarthome.sensors;

public class Luminosity {
    private long luminosity;
    private String roomName;

    public Luminosity(String name) {
        this.luminosity = 25;
        roomName = name;
    }

    public void setLuminosity(long luminosity) {
        this.luminosity = luminosity;
        System.out.println("\tLuminosity in "+ roomName+ " is set to "+ luminosity + "Candela.");
    }

    public long getLuminosity() {
        return luminosity;
    }
}
