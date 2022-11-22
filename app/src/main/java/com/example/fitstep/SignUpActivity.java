package com.example.fitstep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    Button btnSubmit;
    EditText edName, edEmail, edPassword, edRepeatedPassword;
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
        edName = findViewById(R.id.edName);
        edEmail = findViewById(R.id.edPassword);
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
//                try{
//                    finish();
//                }catch(Exception e){
//                    errorMessageDisplay("Cannot create user! Please try again");
//                }
                finish();

            }
        });
    }
}