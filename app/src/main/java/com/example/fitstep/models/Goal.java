package com.example.fitstep.models;

public class Goal {
    private double bmi;
    private double energyIntake;
    private double energyOuput;

    public Goal() {
    }

    public Goal(double bmi, double energyIntake, double energyOuput) {
        this.bmi = bmi;
        this.energyIntake = energyIntake;
        this.energyOuput = energyOuput;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public double getEnergyIntake() {
        return energyIntake;
    }

    public void setEnergyIntake(long energyIntake) {
        this.energyIntake = energyIntake;
    }

    public double getEnergyOuput() {
        return energyOuput;
    }

    public void setEnergyOuput(long energyOuput) {
        this.energyOuput = energyOuput;
    }
}
