package com.smarthome.entities;

import org.json.simple.JSONObject;

public class Windows {
    private Long id;
    private boolean isOpen;

    public Windows(JSONObject obj) {
        id = (Long) obj.get("id");
        isOpen = false;
    }

    public void setStatus(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public boolean isOn() {
        return isOpen;
    }
}
