package com.example.opengymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewReservationsActivity extends AppCompatActivity {

    private ListView myReservationListView;
    private GymReservation g;
    TextView t ;
    ArrayAdapter<GymReservation> listAdapter;
    ArrayList<GymReservation> myList;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reservations);

        myReservationListView = findViewById(R.id.allReservationsListView);
        t = (TextView)findViewById(R.id.reservationMessage);
        myList = WelcomeActivity.firebaseHelper.getGymArrayList();
        if(myList.size() == 0){
            t.setText("NO     RESERVATIONS");
        }else{
            t.setText("SELECT     A     RESERVATION");
        }
        listAdapter = new ArrayAdapter<GymReservation>(
                this, R.layout.list_white_text, myList);
        myReservationListView.setAdapter(listAdapter);
        findViewById(R.id.cancelReservationB).setVisibility(View.INVISIBLE);
        myReservationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                findViewById(R.id.cancelReservationB).setVisibility(View.VISIBLE);
                g = myList.get(position);
            }
        });

    }

    public void cancelClicked(View view){
        WelcomeActivity.firebaseHelper.deleteData(g);
        listAdapter.remove(g);
        listAdapter.notifyDataSetChanged();
        findViewById(R.id.cancelReservationB).setVisibility(View.INVISIBLE);
        if(myList.size() == 0){
            t.setText("NO     RESERVATIONS");
        }else{
            t.setText("SELECT     A     RESERVATION");
        }
    }
}