package com.example.fitstep;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitstep.models.Activity;
import com.example.fitstep.models.GlobalData;
import com.example.fitstep.models.Goal;
import com.example.fitstep.models.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.platforminfo.GlobalLibraryVersionRegistrar;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSignIn, btnSignUp;
    EditText edEmail, edPassword;
    ArrayList<Activity> listOfActivities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        initialize();
    }

    private void initialize() {

        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
                    Log.d("firebase", "connected");
                } else {
                    Log.d("firebase", "not connected");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("firebase", "Listener was cancelled");
            }
        });

        edEmail = findViewById(R.id.username);
        edPassword = findViewById(R.id.password);
        //Dev test
        edEmail.setText("admin");
        edPassword.setText("123");

        btnSignIn = findViewById(R.id.btnLogin);
        btnSignIn.setOnClickListener(this);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);
    }
    private void goToSignUpPage(){
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
    }
    private void goToMainActivity(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
    private void errorMessageDisplay(String errorMessage){
        Toast.makeText(this,errorMessage,Toast.LENGTH_LONG).show();
    }
    private void login(){
        if(edEmail.getText().toString() == null && edPassword.getText().toString() == null){
            Toast.makeText(this, "Email or password invalid",Toast.LENGTH_LONG).show();
        }else{
            String email = edEmail.getText().toString();

            DatabaseReference userChild = GlobalData.userDatabase.child(email);
            userChild.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        String password = snapshot.child("password").getValue().toString();
                        if(!password.equals(edPassword.getText().toString())){
                            errorMessageDisplay("Password invalid");
                        }else{
                            String name = snapshot.child("name").toString();
                            GlobalData.loggedUser = new User(name, email,password);
                            if(snapshot.child("urlProfilePicture")!=null){
                                GlobalData.loggedUser.setUrlProfilePicture(snapshot.child("urlProfilePicture").getValue().toString());
                            }
                            if(snapshot.child("listOfActivities").getValue() != null){
                                listOfActivities = new ArrayList<Activity>();
                                GlobalData.userDatabase.child(GlobalData.loggedUser.getEmail())
                                        .child("listOfActivities")
                                        .addChildEventListener(new ChildEventListener() {
                                            @Override
                                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                                Activity act = snapshot.getValue(Activity.class);
                                                listOfActivities.add(act);
                                                GlobalData.loggedUser.setListOfActivities(listOfActivities);
                                            }

                                            @Override
                                            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                            }

                                            @Override
                                            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                                            }

                                            @Override
                                            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                            }
                            if(snapshot.child("goal").getValue()!=null){
                                Goal goal = snapshot.child("goal").getValue(Goal.class);
                                GlobalData.loggedUser.setGoal(goal);
                            }
                            goToMainActivity();
                        }

                    }else{
                        errorMessageDisplay("Email invalid");
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btnSignUp:
                goToSignUpPage();
                break;
            case R.id.btnLogin:
                login();
                break;
        }

    }
}