package com.example.opengymapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SelectGymActivity extends AppCompatActivity {
    Button community,birchwood,ymca;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_gym);
        community=(Button)findViewById(R.id.community);
        birchwood=(Button)findViewById(R.id.birchwood);
        ymca=(Button)findViewById(R.id.ymca);


        community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SelectGymActivity.this, GymInformationActivity.class);
                intent.putExtra("message","Community    Gym");
                startActivity(intent);
            }
        });
        birchwood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SelectGymActivity.this, GymInformationActivity.class);
                intent.putExtra("message","Birchwood    Gym");
                startActivity(intent);
            }
        });
        ymca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SelectGymActivity.this, GymInformationActivity.class);
                intent.putExtra("message","YMCA    Gym");
                startActivity(intent);
            }
        });
    }

//    public void gymSelected(View view){
//        Intent intent = new Intent(SelectGymActivity.this,
//                GymInformationActivity.class);
//        startActivity(intent);
//    }

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