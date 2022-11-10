package com.example.opengymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GymInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_information);
    }

    public void createReservation(View view) {
        Intent intent = new Intent(GymInformationActivity.this,
                CreateReservationActivity.class);
        startActivity(intent);
    }

    public void viewReservation(View view){
        Intent intent = new Intent(GymInformationActivity.this,
                ViewReservationsActivity.class);
        startActivity(intent);
    }

    public void viewMap(View view){
        Intent intent = new Intent(GymInformationActivity.this,
                ViewReservationsActivity.class);
        startActivity(intent);
    }

}