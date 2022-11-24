package com.example.fitstep.models;

public class Goal {
    private double bmi;
    private long energyIntake;
    private long energyOuput;

    public Goal() {
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public long getEnergyIntake() {
        return energyIntake;
    }

    public void setEnergyIntake(long energyIntake) {
        this.energyIntake = energyIntake;
    }

    public long getEnergyOuput() {
        return energyOuput;
    }

    public void setEnergyOuput(long energyOuput) {
        this.energyOuput = energyOuput;
    }
}
