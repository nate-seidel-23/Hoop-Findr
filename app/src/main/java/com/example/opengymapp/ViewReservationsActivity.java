package com.example.opengymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewReservationsActivity extends AppCompatActivity {

    private ListView myMemoryListView;
    public static final String CHOSEN_RESERVATION = "chosen reservation";
    private GymReservation g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reservations);

        // find listView in xml
        myMemoryListView = findViewById(R.id.allMemoriesListView);
        // get ArrayList of data from firebase
        ArrayList<GymReservation> myList = WelcomeActivity.firebaseHelper.getGymArrayList();
        // bind data to the ArrayAdapter (this is a default adapter
        // The text shown is based on the Memory class toString
        ArrayAdapter<GymReservation> listAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, myList);
        // attaches the listAdapter to my listView
        myMemoryListView.setAdapter(listAdapter);
        // if did custom array set up, use this one
        findViewById(R.id.cancelReservationB).setVisibility(View.INVISIBLE);
        // Create listener to listen for when a Food from the specific Category list is clicked on
        myMemoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                findViewById(R.id.cancelReservationB).setVisibility(View.VISIBLE);
                g = myList.get(position);
            }
        });

    }

    public void cancelClicked(View view){
        WelcomeActivity.firebaseHelper.deleteData(g);
    }
}