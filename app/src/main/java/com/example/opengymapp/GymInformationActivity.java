package com.example.opengymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;

public class GymInformationActivity extends AppCompatActivity {
    TextView gymName;

    public final String TAG = "Halvadia and Seidel";
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_information);
        gymName = (TextView) findViewById(R.id.gymName);
        gymName.setText(getIntent().getStringExtra("message"));
        name = getIntent().getStringExtra("message");

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
                Maps.class);
        intent.putExtra("name", "" + name);
        startActivity(intent);

    }

}