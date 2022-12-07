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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;


public class CreateReservationActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {

    private String selectedDateString = "";
    private ArrayList<String> teamOneNames;
    private ArrayList<String> teamTwoNames;
    private EditText playerNameText;
    private String name;
    GymReservation reservation;
    TextView nameTemp;
    Spinner spinner;
    LinearLayout l;
    ArrayAdapter<String> adapter;
    ArrayList<DocumentSnapshot> usersAtGym;
    String spinnerSelectedText = "none";
    int[] textViewIds;
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

        usersAtGym = WelcomeActivity.firebaseHelper.getAllUsers(getIntent().getStringExtra("nameOfGym"));

        spinner = findViewById(R.id.timeSelector);
        spinner.setVisibility(View.INVISIBLE);
        l = findViewById(R.id.signUpLayout);
        l.setVisibility(View.INVISIBLE);
        textViewIds = new int[] { R.id.playerName1, R.id.playerName2, R.id.playerName3,
                R.id.playerName4, R.id.playerName5, R.id.playerName6, R.id.playerName7, R.id.playerName8,
                R.id.playerName9, R.id.playerName10 };

        reservation = new GymReservation("No name","","","","");


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
            Log.d(TAG, " " + c.get(Calendar.DAY_OF_WEEK));


            if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
                ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, R.layout.spinner_list,
                        getResources().getStringArray(R.array.weekendTimes));
                adapter2.setDropDownViewResource(R.layout.custom_spinner_dropdown_row);
                spinner.setAdapter(adapter2);
                spinner.setOnItemSelectedListener(this);
            }
            else{
                adapter = new ArrayAdapter<>(this, R.layout.spinner_list,
                        getResources().getStringArray(R.array.weekdayTimes));
                adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_row);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(this);
            }
            spinner.setVisibility(View.VISIBLE);
            TextView timeView = findViewById(R.id.timeText);
            timeView.setText("");
        }else{
            Toast.makeText(getApplicationContext(),"Must be a future date",
                    Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerSelectedText = parent.getItemAtPosition(position).toString();
        l.setVisibility(View.VISIBLE);
        if(timeSelected()) {
            TextView t = findViewById(R.id.timeText);
            t.setText(spinnerSelectedText);
        }
        teamOneNames = new ArrayList<String>(Arrays.asList("", "" ,"", "", ""));
        teamTwoNames = new ArrayList<String>(Arrays.asList("", "" ,"", "", ""));

        getUsersOnDate(selectedDateString, spinnerSelectedText);
        for(int i = 0; i < 10; i++){
            nameTemp = (TextView)findViewById(textViewIds[i]);
            if(i < 5){
                nameTemp.setText(teamOneNames.get(i));
            }else{
                nameTemp.setText(teamTwoNames.get(i - 5));
            }
        }

////         use this to fix names not appearing
//        if(hasReservation(selectedDateString, spinnerSelectedText, getIntent().getStringExtra(("nameOfGym")))){
//            teamTwoNames.remove(4);
//            teamTwoNames.add(0, )
//        }

    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) { }

    public void confirmReservationClickedT1(View view) {
        playerNameText = findViewById(R.id.playersName);
        name = playerNameText.getText().toString();

        if(!name.equals("")) {
            if (!hasReservation(selectedDateString, spinnerSelectedText, getIntent().getStringExtra("nameOfGym"))) {
                Boolean b = false;

                for (int i = 0; i < 5; i++) {
                    if (isAvailable(teamOneNames.get(i))) {
                        nameTemp = (TextView) findViewById(textViewIds[i]);
                        nameTemp.setText(name);
                        teamOneNames.set(i, name);
                        reservation.setPlayerName(name);
                        reservation.setTeam("Team 1");
                        reservation.setTime(spinnerSelectedText);
                        reservation.setDate(selectedDateString);
                        reservation.setGym(getIntent().getStringExtra("nameOfGym"));
                        b = true;
                        break;
                    }
                }

                if (b == false) {
                    Toast.makeText(getApplicationContext(), "Sorry " + name
                            + " this team is full this day.", Toast.LENGTH_LONG).show();
                } else {
                    WelcomeActivity.firebaseHelper.addData(reservation);
                }
            } else {
                Toast.makeText(getApplicationContext(), "You already reserved a spot " +
                        "in this game.", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Please enter your name ",
                    Toast.LENGTH_LONG).show();
        }


//        WelcomeActivity.firebaseHelper.getAllUsers("Community");
    }

    public void confirmReservationClickedT2(View view) {
        playerNameText = findViewById(R.id.playersName);
        name = playerNameText.getText().toString();

        if(!name.equals("")){
            if(!hasReservation(selectedDateString, spinnerSelectedText,
                    getIntent().getStringExtra("nameOfGym"))) {
                Boolean b = false;

                for(int i = 0; i < 5; i++) {
                    if (isAvailable(teamTwoNames.get(i))) {
                        nameTemp = (TextView)findViewById(textViewIds[i+5]);
                        nameTemp.setText(name);
                        teamTwoNames.set(i, name);
                        reservation.setPlayerName(name);
                        reservation.setTeam("Team 2");
                        reservation.setTime(spinnerSelectedText);
                        reservation.setDate(selectedDateString);
                        reservation.setGym(getIntent().getStringExtra("nameOfGym"));
                        b = true;
                        break;
                    }
                }

                if(b == false){
                    Toast.makeText(getApplicationContext(),"Sorry " + name
                            + " this team is full this day.", Toast.LENGTH_LONG).show();
                }else {
                    WelcomeActivity.firebaseHelper.addData(reservation);
                }
            }else{
                Toast.makeText(getApplicationContext(), "You already reserved a spot " +
                        "in this game.", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Please enter your name ",
                    Toast.LENGTH_LONG).show();
        }



//        WelcomeActivity.firebaseHelper.getAllUsers("Community");
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

        String[] weekday = getResources().getStringArray(R.array.weekdayTimes);
        String [] weekend = getResources().getStringArray(R.array.weekendTimes);
        boolean optionChose = false;

        for(int i = 1; i < weekday.length - 1; i++) {
            if (spinnerSelectedText.equals(getResources().
                    getStringArray(R.array.weekdayTimes)[i])) {
                optionChose = true;
            }
        }
        return optionChose;
    }

    public void getUsersOnDate(String date, String time){
        String pName = "";
        for(int i = 0; i < usersAtGym.size(); i++){
            if(usersAtGym.get(i).getData().get("date").toString().equals(date) &&
                    usersAtGym.get(i).getData().get("time").toString().equals(time)){

                pName = usersAtGym.get(i).getData().get("playerName").toString();

                if (usersAtGym.get(i).getData().get("team").toString().equals("Team 1")){
                    teamOneNames.remove(4);
                    teamOneNames.add(0, pName);
                }else if(usersAtGym.get(i).getData().get("team").toString().equals("Team 2")){
                    teamTwoNames.remove(4);
                    teamTwoNames.add(0, pName);
                }
            }
        }
    }

    public boolean hasReservation(String d, String t, String n){
        ArrayList<GymReservation> myList = WelcomeActivity.firebaseHelper.getGymArrayList();
        for(int i = 0; i < myList.size(); i++){
            GymReservation g = myList.get(i);
            if(g.getDate().equals(d) && g.getTime().equals(t) && g.getGym().equals(n)){
                return true;
            }
        }
        return false;
    }



}