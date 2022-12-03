package com.example.fitstep.models;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class BodyMesurement implements Serializable {
    private double Height;
    private double Weight;
    private String inputDate;
    private String id;
    public BodyMesurement(double height, double weight, String inputDate) {
        this.id = UUID.randomUUID().toString();
        Height = height;
        Weight = weight;
        this.inputDate = inputDate;
    }
    public BodyMesurement(){

    }

    public String getId() {
        return id;
    }

    public double getHeight() {
        return Height;
    }

    public void setHeight(double height) {
        Height = height;
    }

    public double getWeight() {
        return Weight;
    }

    public void setWeight(double weight) {
        Weight = weight;
    }

    public double BMI(){
        return Math.round(Weight/(Height*Height)*10)/10;
    }

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }

    @Override
    public String toString() {
        return String.valueOf(BMI());
    }
}
