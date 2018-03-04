package com.nextdev.starterhacks;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, LocationListener {
    
    private GoogleApiClient mGoogleApiClient;
    final int PERMISSION_LOCATION = 111;
    private GestureDetectorCompat gestureObject;
    public ArrayList<location> loc = new ArrayList<location>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();

        createShortcut();

        gestureObject = new GestureDetectorCompat(this, new SwipeGesture());
        
        loc.add(new location("371 Columbia Dr", 2,"Broken glass"));
        loc.add(new location("290 Westmount Rd N", 0, "lots of beer cans"));
        loc.add(new location("1665 University Ave", 0, "some cardboard"));
        loc.add(new location("20908 Ring Road", 1,"some rusty tin cans"));
        loc.add(new location("295 Hagey Blvd", 1, "car bumper"));
        // SORTING TO BE ENABLED ONCE GOOGLE MAPS API
        // sortLocations sLoc = new sortLocations();
        // locList = sort(sLoc);

        FragmentManager frag = getSupportFragmentManager();
        frag.beginTransaction().replace(R.id.frag1, new ReducedInfoFragment()).commit();
        frag.beginTransaction().replace(R.id.frag2, new ReducedInfoFragment()).commit();
        frag.beginTransaction().replace(R.id.frag3, new ReducedInfoFragment()).commit();
        frag.beginTransaction().replace(R.id.frag4, new ReducedInfoFragment()).commit();

    }

    private void createShortcut(){
        Intent shortcutIntent = new Intent(getApplicationContext(),MainActivity.class);
        shortcutIntent.setAction(Intent.ACTION_MAIN);
        Intent intent = new Intent();
        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, R.string.app_name);
        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.drawable.ic_launcher_foreground));
        intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        getApplicationContext().sendBroadcast(intent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureObject.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class SwipeGesture extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            if(event2.getX() < event1.getX()) {
                Intent intent = new Intent(
                        MainActivity.this, aboutActivity.class);
                finish();
                startActivity(intent);
            } else {
                Intent intent = new Intent(
                        MainActivity.this, profileActivity.class);
                finish();
                startActivity(intent);
            }
            return true;
        }
    }
    // Called when the user taps the send button
    public void goToInfo(View view) {
        Intent intent = new Intent(this, InfoActivity.class);
        int num = buttonToInt(view);
        intent.putExtra("ADDRESS", loc.get(num).addr);
        intent.putExtra("HAZARD", loc.get(num).haz);
        intent.putExtra("DESCRIPTION", loc.get(num).desc);
        intent.putExtra("DISTANCE", loc.get(num).dist);

        startActivity(intent);
    }

    private int buttonToInt(View view) {
        switch(view.getId()) {
            case R.id.button1:
                return 0;
            case R.id.button2:
                return 1;
            case R.id.button3:
                return 2;
            default:
                return 3;
        }
    }

    // Called when the add button is pressed
    public void goToAdd(View view) {
        Intent intent = new Intent(this, addActivity.class);
        startActivity(intent);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }

            @Override
    public void onConnectionSuspended(int i) {

                    }

            @Override
    public void onConnected(@Nullable Bundle bundle) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_LOCATION);
                        Log.v("DONKEY", "Requesting Permissions");
                    } else {
                        Log.v("Donkey", "starting location services from onConnected");
                        startLocationServices();
                    }
            }

           @Override
    public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                        Log.v("donkey", "Lat:" + latitude + " - Long:" + longitude);
            }

            @Override
    protected void onStart() {
                mGoogleApiClient.connect();
               super.onStart();
            }

            @Override
    protected void onStop() {
                mGoogleApiClient.disconnect();
                super.onStop();
            }

            @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);

                       switch (requestCode) {
                        case PERMISSION_LOCATION: {
                                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                                        startLocationServices();
                                        Log.v("DONKEY", "Permission Granted - Starting Services");
                                    } else {
                                        //Show a dialog saying something like "I can't see your location"
                                                Log.v("DONKEY", "Permission not granted");
                                   }
                            }
                    }
            }

           public void startLocationServices() {
               Log.v("DONKEY", "starting location services called");

                       try {
                       LocationRequest req = LocationRequest.create().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, req, this);
                        Log.v("DONKEY", "Requestion Location Updates");
                    } catch (SecurityException exception) {
                        //Should dialog to user
                                Log.v("DONKEY", exception.toString());
                    }


                            }
}
