package com.smarthome;

import com.smarthome.entities.*;
import com.smarthome.helpers.Time;
import com.smarthome.sensors.Activity;
import com.smarthome.sensors.Luminosity;
import com.smarthome.sensors.Temperature;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.HashMap;

public class Room {

    //user settings
    private String name;
    private String sleepTime;
    private String wakeUpTime;


    private final Luminosity luminosity;
    private final Temperature temperature;
    private final Activity activity;

    private Time time;


    private final HashMap<Long, Lights> lights;
    private final HashMap<Long, Ac> ac;
    private final HashMap<Long, Fan> fan;
    private final HashMap<Long, Windows> windows;
    private final HashMap<Long, WindowShades> windowsScreen;
    private final HashMap<Long, Door> door;

    public Room(JSONObject data) {

        name = (String) data.get("name");
        sleepTime = (String) data.get("sleep time");
        wakeUpTime = (String) data.get("wakeup time");

        luminosity = new Luminosity(name);
        temperature = new Temperature(name);
        activity = new Activity(name);

        time = new Time();

        lights = new HashMap<Long, Lights>();
        ac = new HashMap<Long, Ac>();
        fan = new HashMap<Long, Fan>();
        door = new HashMap<Long, Door>();
        windowsScreen = new HashMap<Long, WindowShades>();
        windows = new HashMap<Long, Windows>();

        JSONArray lightArray = (JSONArray) data.get("light");
        for (Object o : lightArray) {
            JSONObject obj = (JSONObject) o;
            lights.put((Long) obj.get("id"), new Lights(obj, name));
            System.out.println("\t" + obj.get("name")+" setting up complete.");
        }

        JSONArray fanArray = (JSONArray) data.get("fan");
        for (Object o : fanArray) {
            JSONObject obj = (JSONObject) o;
            fan.put((Long) obj.get("id"), new Fan(obj, name));
            System.out.println("\t"+obj.get("name")+" setting up complete.");
        }

        JSONArray acArray = (JSONArray) data.get("Ac");
        for (Object o : acArray) {
            JSONObject obj = (JSONObject) o;
            ac.put((Long) obj.get("id"), new Ac(obj, name));
            System.out.println("\t"+ obj.get("name")+" setting up complete.");
        }

        JSONArray windowArray = (JSONArray) data.get("windows");
        for (Object o : windowArray) {
            JSONObject obj = (JSONObject) o;
            windows.put((Long) obj.get("id"), new Windows(obj, name));
            System.out.println("\t"+ obj.get("name")+" setting up complete.");
        }

        JSONArray windowShadeArray = (JSONArray) data.get("windows Shade");
        for (Object o : windowShadeArray) {
            JSONObject obj = (JSONObject) o;
            windowsScreen.put((Long) obj.get("id"), new WindowShades(obj, name));
            System.out.println("\t"+ obj.get("name")+" setting up complete.");
        }

        JSONArray doorArray = (JSONArray) data.get("Door");
        for (Object o : doorArray) {
            JSONObject obj = (JSONObject) o;
            door.put((Long) obj.get("id"), new Door(obj, name));
            System.out.println("\t"+ obj.get("name")+" setting up complete.");
        }
    }

    public void updateSensorData(JSONObject obj) {
        System.out.println("Updating sensor data...");
        luminosity.setLuminosity((Long) obj.get("Luminosity"));
        temperature.setTemperature((Long) obj.get("Temperature"));
        activity.setPersonPresent((Long) obj.get("Activity") == 1);
        System.out.println("Context aware behaviour...");
        cab();
        System.out.println("Dot.\n");
    }

    public void cab() {
        
//        Turns off everything if there is no person in the room
        if(!activity.isPersonPresent()) {
            for(Fan f: fan.values()) { f.turnOff(); }
            for(Ac a: ac.values()) { a.turnOff(); }
            for(Lights l: lights.values()) { l.turnOff(); }
        } else {

//            Regulating the temperature in the room
            long temp = temperature.getTemperature();
            if(temp <20) {
                for(Ac a: ac.values()) { a.turnOff(); }
                for(Windows w: windows.values()) {w.close();}
            } else if(temp <26) {
                for(Fan f: fan.values()) { f.turnOff(); }
            } else if(temp <32) {
                for(Fan f: fan.values()) { f.turnOn(); }
            } else {
                for(Ac a: ac.values()) { a.turnOn(); }
                for(Windows w: windows.values()) {w.close();}
                for(WindowShades w: windowsScreen.values()) {w.close();}
                for(Door d: door.values()) {d.close();}
                for(Lights l: lights.values()) {
                    if("Dim lights".equals(l.getName())) {
                        l.turnOn();
                    }
                }
            }

//            Regulating the luminance of the room
            long lumi = luminosity.getLuminosity();
            if(lumi <= 200) {
                if(time.isGivenTimeIsBetween(sleepTime, wakeUpTime)) {
                    for(WindowShades w: windowsScreen.values()) {w.close();}
                    for(Lights l: lights.values()) {
                        if("Night lights".equals(l.getName())) {
                            l.turnOn();
                        } else {
                            l.turnOff();
                        }
                    }
                } else {
                    String s;
                    if(lumi<=10){
                        s = "Bright lights";
                        for(WindowShades w: windowsScreen.values()) {w.close();}
                    } else {
                        s = "Dim lights";
                        for(WindowShades w: windowsScreen.values()) {w.open();}
                    }
                    for(Lights l: lights.values()) {
                        if(s.equals(l.getName())) {
                            l.turnOn();
                        } else {
                            l.turnOff();
                        }
                    }
                }
            } else if(lumi<=800) {
                for(WindowShades w: windowsScreen.values()) {w.open();}
                for(Lights l: lights.values()) { l.turnOff(); }
            } else {
                for(WindowShades w: windowsScreen.values()) {w.close();}
                for(Lights l: lights.values()) { l.turnOff(); }
            }
        }
    }


}
