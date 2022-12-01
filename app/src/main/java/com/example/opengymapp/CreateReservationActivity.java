package com.example.opengymapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class CreateReservationActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private String currentDateString = "";
    private ArrayList<String> teamOneNames;
    private ArrayList<String> teamTwoNames;
    private EditText playerNameText;
    private String name;
    GymReservation reservation;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reservation);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        Button button = (Button) findViewById(R.id.dateButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        teamOneNames = new ArrayList<String>(Arrays.asList("", "" ,"", "", ""));
        teamTwoNames = new ArrayList<String>(Arrays.asList("", "" ,"", "", ""));
        reservation = new GymReservation("","","","","");

    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        reservation.setDate(currentDateString);

    }

    public void confirmReservationClickedT1(View view) {
        playerNameText = findViewById(R.id.playerName);
        name = playerNameText.getText().toString();

        reservation.setDate(currentDateString);

        if(reservation.getPlayerName().equals("")) {
            Boolean b = false;

            for(int i = 0; i < 5; i++) {
                if (isAvailable(teamOneNames.get(i))) {
                    teamOneNames.set(i, name);
                    reservation.setPlayerName(name);
                    b = true;
                }
            }

            if(b == false){
                Toast.makeText(getApplicationContext(),"Sorry " + name
                        + " this team is full this day.", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "You already reserved a spot " +
                    "in this game.", Toast.LENGTH_LONG).show();
        }

        WelcomeActivity.firebaseHelper.addData(reservation);
    }

    public void confirmReservationClickedT2(View view) {

        if(reservation.getPlayerName().equals("")) {
            boolean b = false;

            for(int i = 0; i < 5; i++){
                if (isAvailable(teamTwoNames.get(i))) {
                    teamTwoNames.set(i, name);
                    reservation.setPlayerName(name);
                    b = true;
                }
            }

            if(b == false){
                Toast.makeText(getApplicationContext(),"Sorry " + name
                        + " this team is full this day.", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "You already reserved a spot " +
                    "in this game.", Toast.LENGTH_LONG).show();
        }

        WelcomeActivity.firebaseHelper.addData(reservation);
    }


    // we will need to change this method to confirm that the user hasn't previously entered
    // a name on that date/time
    public boolean isAvailable(String s){
            if(s.equals("")) {
                return true;
            }else{
                return false;
            }
    }

}