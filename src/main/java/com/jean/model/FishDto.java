package com.jean.model;

import com.jean.entity.Message;
import com.jean.entity.RangeWeatherParameters;

import java.util.List;
import java.util.Map;

public class FishDto {
    
    private int id;
    
    private String name;
    
    private String description;
    
    private Message message;

    private Map<String, List<RangeWeatherParameters>> hungry;

    private Map<String, List<RangeWeatherParameters>> location;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Map<String, List<RangeWeatherParameters>> getHungry() {
        return hungry;
    }

    public void setHungry(Map<String, List<RangeWeatherParameters>> hungry) {
        this.hungry = hungry;
    }

    public Map<String, List<RangeWeatherParameters>> getLocation() {
        return location;
    }

    public void setLocation(Map<String, List<RangeWeatherParameters>> location) {
        this.location = location;
    }
}