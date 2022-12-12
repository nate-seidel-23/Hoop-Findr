package com.example.opengymapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GymInformationActivity extends AppCompatActivity {
    TextView gymName;
    ImageView image;
    public final String TAG = "TAG";
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_information);
        gymName = (TextView) findViewById(R.id.gymName);
        name = getIntent().getStringExtra("message");
        gymName.setText(name);
        image = findViewById(R.id.imageOfCourt);
        image.setImageResource(getIntent().getIntExtra("image", 0));

    }

    public void createReservation(View view) {
        Intent intent = new Intent(GymInformationActivity.this,
                CreateReservationActivity.class);
        intent.putExtra("nameOfGym", "" + name);
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
    @SuppressLint("RestrictedApi")
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        if(menu instanceof MenuBuilder){
            MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.SignOut:
                Intent intent1 = new Intent(this, SignOut.class);
                this.startActivity(intent1);
                return true;
            case R.id.Reservation:
                Intent intent2 = new Intent(this, ViewReservationsActivity.class);
                this.startActivity(intent2);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}