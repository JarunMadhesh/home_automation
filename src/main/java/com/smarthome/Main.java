package com.smarthome;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {

        FileReader reader =  new FileReader(".\\src\\main\\java\\com\\smarthome\\jsonFiles\\setup.json");

        JSONParser jsonParser = new JSONParser();
        JSONObject obj =(JSONObject) jsonParser.parse(reader);

        Home home = new Home(obj);

        // context 1
        FileReader reader2 =  new FileReader(".\\src\\main\\java\\com\\smarthome\\jsonFiles\\sensorData.json");
        JSONArray obj2 =(JSONArray) jsonParser.parse(reader2);
        for(Object o: obj2) {
            home.updateSensorData((JSONObject) o);
        }
    }
}
