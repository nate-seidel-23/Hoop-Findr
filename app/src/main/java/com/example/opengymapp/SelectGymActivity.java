package com.example.opengymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SelectGymActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_gym);
    }

    public void createReservation(View view) {
        Intent intent = new Intent(SelectGymActivity.this, CreateReservationActivity.class);
        startActivity(intent);
    }



}