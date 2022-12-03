package com.example.fitstep.models;

import android.text.format.DateUtils;

import androidx.annotation.NonNull;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class User {
    private String name;
    private String email;
    private String password;
    private String urlProfilePicture;
    private ArrayList<Activity> listOfActivities;
    private ArrayList<Food> listOfFoods;
    private ArrayList<BodyMesurement> trackBodyMesurements;
    private Goal goal;
    public  User(){}
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.urlProfilePicture = "";
        this.listOfActivities = new ArrayList<Activity>();
        this.listOfFoods = new ArrayList<Food>();
        this.trackBodyMesurements = new ArrayList<BodyMesurement>();
        this.goal = new Goal();
    }

    @NonNull
    @Override
    public String toString() {
        return email;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
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

    public String getUrlProfilePicture() {
        return urlProfilePicture;
    }

    public void setUrlProfilePicture(String urlProfilePicture) {
        this.urlProfilePicture = urlProfilePicture;
    }

    public ArrayList<Activity> getListOfActivities() {
        return listOfActivities;
    }

    public void setListOfActivities(ArrayList<Activity> listOfActivities) {
        this.listOfActivities = listOfActivities;
    }

    public ArrayList<Food> getListOfFoods() {
        return listOfFoods;
    }

    public void setListOfFoods(ArrayList<Food> listOfFoods) {
        this.listOfFoods = listOfFoods;
    }

    public ArrayList<BodyMesurement> getTrackBodyMesurements() {
        return trackBodyMesurements;
    }

    public void setTrackBodyMesurements(ArrayList<BodyMesurement> trackBodyMesurements) {
        this.trackBodyMesurements = trackBodyMesurements;
    }

    public ArrayList<Activity> getListOfActivitiesForDate(String date){
        ArrayList<Activity> acts = new ArrayList<Activity>();
        for(Activity act : listOfActivities){
            if(date.equals(act.getDateDone())){
                acts.add(act);
            }
        }
        return acts;
    }

    public ArrayList<Food> getListOfFoodsConsumedAtDate(String date){
        ArrayList<Food> foods= new ArrayList<Food>();
        for(Food food : listOfFoods){
            if(date.equals(food.getDateConsumed())){
                foods.add(food);
            }
        }
        return foods;
    }
    public BodyMesurement getBodyMesurementAtDate(String date){
        BodyMesurement bd = new BodyMesurement();
        for(BodyMesurement mes : trackBodyMesurements){
            if(mes.getInputDate().equals(date)){
                bd = mes;
            }
        }
        return bd;
    }
    public int CaloriesBurntToday(ArrayList<Activity> activities){
        int sum = 0;
        for(Activity act : activities){
            if(GlobalData.caloriesBurntByExercise.containsKey(act.getName())){
                double hour = act.getNbOfHoursDone();
                int caloryBurntPer30Mins = GlobalData.caloriesBurntByExercise.get(act.getName());
                double caloryBurnt = hour*caloryBurntPer30Mins/0.5;
                sum += caloryBurnt;
            }
        }
        return sum;
    }
}
