package com.example.opengymapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class CreateReservationActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    String currentDateString;
    GymReservation reservation = new GymReservation();

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
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
            reservation.setDate(currentDateString);
            //need to do set the date in here

            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(currentDateString);
        }






}