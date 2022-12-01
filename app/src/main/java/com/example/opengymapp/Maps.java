package com.example.opengymapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps extends AppCompatActivity implements OnMapReadyCallback{
//    private double long;
//    private double lat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
//    if extra.getIntent().message == birchwood {
            LatLng birchwood = new LatLng(42.0930720186, -88.0558555322);
            googleMap.addMarker(new MarkerOptions()
                    .position(birchwood)
                    .title("Marker at Birchwood Gym"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(birchwood));
//        }
    }
}
