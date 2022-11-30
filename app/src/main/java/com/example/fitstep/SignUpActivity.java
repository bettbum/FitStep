package com.example.fitstep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitstep.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {
    Button btnSubmit;
    EditText edName, edEmail, edPassword, edRepeatedPassword;
    DatabaseReference userDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initialize();
    }
    private void errorMessageDisplay(String errorMessage){
        Toast.makeText(this,errorMessage,Toast.LENGTH_LONG).show();
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
        edName = findViewById(R.id.edName);
        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);
        edRepeatedPassword = findViewById(R.id.edRepeatedPassword);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edName.getText() == null){
                    errorMessageDisplay("You need to enter your name");
                }
                if(edEmail.getText()== null){
                    errorMessageDisplay("You need to enter your email");
                }
                if(edPassword.getText()==null){
                    errorMessageDisplay("You need to enter your password");
                }
                if(edRepeatedPassword.getText()==null){
                    errorMessageDisplay("You need to confirm your password");
                }
                if(!edPassword.getText().toString().equals(edRepeatedPassword.getText().toString())){
                    errorMessageDisplay("Invalid password");
                }
                String name = edName.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                User user = new User(name, email, password);
                userDatabase = FirebaseDatabase.getInstance().getReference("user");
                userDatabase.child(user.getEmail()).setValue(user);
//                try{
//                    finish();
//                }catch(Exception e){
//                    errorMessageDisplay("Cannot create user! Please try again");
//               }
                finish();

            }
        });
    }
}