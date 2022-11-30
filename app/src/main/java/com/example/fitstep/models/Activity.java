package com.example.fitstep.models;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Activity implements Serializable {
    private String name;
    private double nbOfHoursDone;
    private String id;

    public String getId() {
        return id;
    }


    public String getDateDone() {
        return dateDone;
    }

    public void setDateDone(String dateDone) {
        this.dateDone = dateDone;
    }

    private String dateDone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNbOfHoursDone() {
        return nbOfHoursDone;
    }

    public void setNbOfHoursDone(double nbOfHoursDone) {
        this.nbOfHoursDone = nbOfHoursDone;
    }



    public Activity(String name, double nbOfHoursDone, String dateDone) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.nbOfHoursDone = nbOfHoursDone;
        this.dateDone = dateDone;
    }
    public Activity(){}

    @Override
    public String toString() {
        return name;
    }
}
