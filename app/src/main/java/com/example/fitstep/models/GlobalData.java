package com.example.fitstep.models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class GlobalData {
    public static User loggedUser;
    public static DatabaseReference userDatabase = FirebaseDatabase.getInstance().getReference("user");
    public static final Map<String, Integer> caloriesBurntByExercise = new HashMap<>();
    static {

        //Calory Burnt By Exercise list
        caloriesBurntByExercise.put("aerobics",211);
        caloriesBurntByExercise.put("stationary bike",176);
        caloriesBurntByExercise.put("dusting",70);
        caloriesBurntByExercise.put("gardening",176);
        caloriesBurntByExercise.put("grocery shopping",106);
        caloriesBurntByExercise.put("hiking",211);
        caloriesBurntByExercise.put("house cleaning",106);
        caloriesBurntByExercise.put("jogging",247);
        caloriesBurntByExercise.put("cooking",70);
        caloriesBurntByExercise.put("yoga",141);
        caloriesBurntByExercise.put("weightlifting",106);
        caloriesBurntByExercise.put("run",200);
        caloriesBurntByExercise.put("walk",106);




    }

}
