package com.example.kachi.storyfinderfinale.model;

/**
 * Created by KaChi on 2016/12/11.
 */

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Event implements Serializable {

    private  String mName;

    private String lon;

    private String lat;

    private String description;

    private String poster;

    private String city;

    private String rating;

    private UUID mUUID;

    private String link;

    private static Event sEvent;

    public static List<Event> parseJson(JSONObject jsonObject) throws JSONException{
        List<Event> events = new ArrayList<>();
        // Check if the JSONObject has object with key "Search"
        if(jsonObject.has("results")){
            // Get JSONArray from JSONObject
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            for(int i = 0; i < jsonArray.length(); i++){
                // Create new Movie object from each JSONObject in the JSONArray
                events.add(new Event(jsonArray.getJSONObject(i)));
            }
        }
        return events;
    }

    private Event() {
    }

    public static Event get() {
        if(sEvent == null) {
            sEvent = new Event();
        }
        return sEvent;
    }

    private Event(JSONObject jsonObject) throws JSONException {
        if(jsonObject.has("name")) this.setName(jsonObject.getString("name"));
        if(jsonObject.has("lat")) this.setLat(jsonObject.getString("lat"));
        if(jsonObject.has("lon")) this.setLon(jsonObject.getString("lon"));
        if(jsonObject.has("rating")) this.setRating(jsonObject.getString("rating"));
        if(jsonObject.has("link")) this.setDescription(jsonObject.getString("description"));
        if(jsonObject.has("description")) this.setLink(jsonObject.getString("link"));
        if(jsonObject.has("city")) this.setCity(jsonObject.getString("city"));
        if(jsonObject.getJSONObject("group_photo").has("photo_link")) this.setPoster(jsonObject.getJSONObject("group_photo").getString("photo_link"));
        mUUID = UUID.randomUUID();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getUUID() {
        return mUUID;
    }

    public void setUUID(UUID UUID) {
        mUUID = UUID;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
