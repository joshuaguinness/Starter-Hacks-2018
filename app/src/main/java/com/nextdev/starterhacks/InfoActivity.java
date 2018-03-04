package com.nextdev.starterhacks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class InfoActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        
        // Imported Vars
        String address = bundle.getString("ADDRESS");
        int hazard = bundle.getInt("HAZARD");
        String description = bundle.getString("DESCRIPTION");
        double distance = bundle.getDouble("DISTANCE");
        
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map
        setContentView(R.layout.activity_info);
        //Get the SupportMapFragment and request notification
        //when the map is read to be used
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Add a marker in Sydney, Australia, and move the map's camera to the same location
        LatLng sydney = new LatLng(-33.852, 151.211);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
