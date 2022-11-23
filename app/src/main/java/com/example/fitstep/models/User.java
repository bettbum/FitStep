package com.example.fitstep.models;

import java.util.UUID;

public class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private Activity listOfActivities;
    private Food listOfFoods;
    private BodyMesurement trackOfBodyMesurement;

    public User(String name, String email, String password) {
        id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.password = password;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Activity getListOfActivities() {
        return listOfActivities;
    }

    public void setListOfActivities(Activity listOfActivities) {
        this.listOfActivities = listOfActivities;
    }

    public Food getListOfFoods() {
        return listOfFoods;
    }

    public void setListOfFoods(Food listOfFoods) {
        this.listOfFoods = listOfFoods;
    }

    public BodyMesurement getTrackOfBodyMesurement() {
        return trackOfBodyMesurement;
    }

    public void setTrackOfBodyMesurement(BodyMesurement trackOfBodyMesurement) {
        this.trackOfBodyMesurement = trackOfBodyMesurement;
    }
}
