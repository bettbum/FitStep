package com.example.fitstep.models;

import java.util.Date;

public class Food {
    private String name;
    private int quantityConsumed;
    private String dateConsumed;

    public Food(String name, int quantityConsumed, String dateConsumed) {
        this.name = name;
        this.quantityConsumed = quantityConsumed;
        this.dateConsumed = dateConsumed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantityConsumed() {
        return quantityConsumed;
    }

    public void setQuantityConsumed(int quantityConsumed) {
        this.quantityConsumed = quantityConsumed;
    }

    public String getDateConsumed() {
        return dateConsumed;
    }

    public void setDateConsumed(String dateConsumed) {
        this.dateConsumed = dateConsumed;
    }

    @Override
    public String toString() {
        return name;
    }
}
