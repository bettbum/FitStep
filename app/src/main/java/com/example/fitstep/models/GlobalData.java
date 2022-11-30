package com.example.fitstep.models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class GlobalData {
    public static User loggedUser;
    public static DatabaseReference userDatabase = FirebaseDatabase.getInstance().getReference("user");
}
