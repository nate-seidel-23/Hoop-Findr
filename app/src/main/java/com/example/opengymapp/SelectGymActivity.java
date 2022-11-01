package com.example.opengymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SelectGymActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_gym);
    }

    Gym ymca = new Gym("YMCA", 3,
            "1400 W Northwest Hwy, Palatine, IL 60067");

    Gym community = new Gym("Palatine Community Center", 2,
            "1250 E Wood St, Palatine, IL 60067");

    Gym birchwood = new Gym("Birchwood recreation center", 1,
            "435 W. Illinois Avenue");
}