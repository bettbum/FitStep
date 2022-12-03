package com.example.fitstep.models;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable {
    private String label;
    private String description;
    private String image;
    private ArrayList<String> ingredients;
    private String cuisineTypes;
    private String calories;

    public Recipe() {
    }

    public Recipe(String label, String description, String image, ArrayList<String> ingredients, String cuisineTypes, String calories) {
        this.label = label;
        this.description = description;
        this.image = image;
        this.ingredients = ingredients;
        this.cuisineTypes = cuisineTypes;
        this.calories = calories;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getCuisineTypes() {
        return cuisineTypes;
    }

    public void setCuisineTypes(String cuisineTypes) {
        this.cuisineTypes = cuisineTypes;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    @NonNull
    @Override
    public String toString() {
        return label;
    }
}
