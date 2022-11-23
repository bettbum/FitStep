package com.example.fitstep.models;

import java.util.Date;

public class BodyMesurement {
    private double Height;
    private double Weight;
    private String inputDate;

    public BodyMesurement(double height, double weight, String inputDate) {
        Height = height;
        Weight = weight;
        this.inputDate = inputDate;
    }
    public BodyMesurement(){

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
        return Weight/(Height*Height);
    }

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }

    @Override
    public String toString() {
        return "BodyMesurement{" +
                "Height=" + Height +
                ", Weight=" + Weight +
                ", inputDate=" + inputDate +
                '}';
    }
}
