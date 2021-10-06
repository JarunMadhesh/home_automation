package com.smarthome;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Home {
    private String homeName;
    HashMap<Long , Room> rooms;

    public Home(JSONObject obj) {
        rooms = new HashMap<Long, Room>();
        homeName = (String) obj.get("home name");
        JSONArray roomsData = (JSONArray) obj.get("rooms");

        for(int i=0; i<roomsData.size(); i++) {

            JSONObject room = (JSONObject) roomsData.get(i);
            System.out.println(room.get("name")+" setting up.");
            rooms.put((Long) room.get("id"), new Room(room));
            System.out.println(room.get("name")+" setting up complete.\n");
        }
    }

    public void updateSensorData(JSONObject obj) {
        rooms.get((Long) obj.get("room id")).updateSensorData(obj);
    }

}
