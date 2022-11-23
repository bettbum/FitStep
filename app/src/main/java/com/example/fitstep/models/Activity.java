package com.example.fitstep.models;

import java.util.Date;

public class Activity {
    private String name;
    private double nbOfHoursDone;
    private Date dateDone;

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

    public Date getDateDone() {
        return dateDone;
    }

    public void setDateDone(Date dateDone) {
        this.dateDone = dateDone;
    }

    public Activity(String name, double nbOfHoursDone, Date dateDone) {
        this.name = name;
        this.nbOfHoursDone = nbOfHoursDone;
        this.dateDone = dateDone;
    }

    @Override
    public String toString() {
        return name;
    }
}
