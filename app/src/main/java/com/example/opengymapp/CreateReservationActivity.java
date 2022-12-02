package com.example.opengymapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.widget.Toast;


public class CreateReservationActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {

    private String selectedDateString = "";
    private ArrayList<String> teamOneNames;
    private ArrayList<String> teamTwoNames;
    private EditText playerNameText;
    private String name;
    GymReservation reservation;
    TextView t;
    Spinner spinner;
    String spinnerSelectedText = "none";
    public final String TAG = "Denna";



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

        spinner = findViewById(R.id.timeSelector);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_list,
                getResources().getStringArray(R.array.weekdayTimes));

        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_row);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        spinner.setVisibility(View.INVISIBLE);

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
        selectedDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        Log.d(TAG, " " + c.get(Calendar.YEAR) + c.get(Calendar.DAY_OF_MONTH));

        Calendar today = Calendar.getInstance();
        Date currentDate = new Date(today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH),
                today.get(Calendar.YEAR));
        Log.d(TAG,  "" + currentDate.getDay());

        if(currentDate.getDay() < dayOfMonth &&
                currentDate.getMonth() <= month && currentDate.getYear() == year){
            reservation.setDate(selectedDateString);
            TextView t = findViewById(R.id.dateText);
            t.setText(selectedDateString);
            spinner.setVisibility(View.VISIBLE);
        }else{
            Toast.makeText(getApplicationContext(),"Must be a future date",
                    Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerSelectedText = parent.getItemAtPosition(position).toString();
        if(timeSelected()) {
            TextView t = findViewById(R.id.timeText);
            t.setText(spinnerSelectedText);
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) { }

    public void confirmReservationClickedT1(View view) {
        playerNameText = findViewById(R.id.playerName);
        name = playerNameText.getText().toString();
        reservation.setTime(spinnerSelectedText);
        reservation.setDate(selectedDateString);

        if(reservation.getPlayerName().equals("")) {
            Boolean b = false;

            for(int i = 0; i < 5; i++) {
                if (isAvailable(teamOneNames.get(i))) {
                    teamOneNames.set(i, name);
                    reservation.setPlayerName(name);
                    b = true;
                    t = (TextView)findViewById(R.id.playerName + i + 1);
                    t.setText(name);
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

    public boolean timeSelected(){

        String[] weekend = getResources().getStringArray(R.array.weekdayTimes);
        boolean optionChose = false;

        for(int i = 1; i < weekend.length - 1; i++)
        if(spinnerSelectedText.equals(getResources().
                getStringArray(R.array.weekdayTimes)[i])){
            optionChose = true;
        }

        return optionChose;
    }

}