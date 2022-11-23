package com.example.fitstep.models;

import java.util.Date;

public class BodyMesurement {
    private double Height;
    private double Weight;
    private Date inputDate;

    public BodyMesurement(double height, double weight, Date inputDate) {
        Height = height;
        Weight = weight;
        this.inputDate = inputDate;
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

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }
    public double BMI(){
        return Weight/(Height*Height);
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
