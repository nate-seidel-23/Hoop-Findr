package com.example.opengymapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps extends AppCompatActivity implements OnMapReadyCallback{
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);

        name = getIntent().getStringExtra("name");

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        double lon;
        double lat;
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        if (name.equals("Community")){
                lon = -88.038000;
                lat = 42.114100;
            }else if (name.equals("Birchwood")){
                lon = -88.0558555322;
                lat = 42.0930720186;
            }else{
                lon = -88.075760;
                lat = 42.127190;
        }

        LatLng birchwood = new LatLng(lat, lon);
        googleMap.addMarker(new MarkerOptions()
                .position(birchwood)
                .title("Marker at " + name + " Gym"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(birchwood));

    }
}

