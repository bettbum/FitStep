package com.example.fitstep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSignIn, btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        initialize();
    }

    private void initialize() {
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
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btnSignUp:
                goToSignUpPage();
                break;
            case R.id.btnLogin:
                goToMainActivity();
                break;


        }

    }
}