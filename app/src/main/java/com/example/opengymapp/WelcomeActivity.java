package com.example.opengymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WelcomeActivity extends AppCompatActivity {

    Button logInB, signUpB;
    EditText emailET, passwordET;

    String userName, password;

    public static FirebaseHelper firebaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        firebaseHelper = new FirebaseHelper();
        logInB = findViewById(R.id.logIn);
        signUpB = findViewById(R.id.signUp);
        emailET = findViewById(R.id.email);
        passwordET = findViewById(R.id.password);
    }

    public void logInClicked(View view){
        Intent intent = new Intent(WelcomeActivity.this,
                SelectGymActivity.class);
        startActivity(intent);
    }


}