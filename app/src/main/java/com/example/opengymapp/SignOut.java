package com.example.opengymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SignOut extends AppCompatActivity {

    public final String TAG = "Halvadia";
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_out);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
    }
    public void logOutClicked(View view) {
        WelcomeActivity.firebaseHelper.logOutUser();
        Log.i(TAG, "user logged out");
        Intent intent = new Intent(SignOut.this, WelcomeActivity.class);
        startActivity(intent);
    }
    public void backClick(View arg0) {
        // TODO Auto-generated method stub
        Intent intent = new Intent(getApplicationContext(), SelectGymActivity.class);
        startActivity(intent);
    }
}