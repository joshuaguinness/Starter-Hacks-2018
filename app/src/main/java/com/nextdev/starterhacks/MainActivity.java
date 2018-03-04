package com.nextdev.starterhacks;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GestureDetectorCompat gestureObject;
    public ArrayList<location> loc = new ArrayList<location>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loc.add(new location("35 Idleswift Dr",2,"dangerous"));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createShortcut();
        fileIO io = new fileIO();
        writeOut("StarterHacks2018.txt");
        readIn("StarterHacks2018.txt");


        gestureObject = new GestureDetectorCompat(this, new SwipeGesture());
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

    public void readIn(String fileName) {
        Log.d("fileIO", "entered");
        String ret = "";
        try {
            FileInputStream inputStream = openFileInput(fileName);
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }
                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (Exception e) {
            Log.d("fileIO", e.getMessage());
        }
        Log.d("fileIO", "read from file: " + ret);
        String[] items = ret.split("##");
        for (int i = 0; i < items.length; i++) {
            String[] parts = items[i].split(",");
            location L = new location(parts[0], Integer.parseInt(parts[1]), parts[2], 1000);
            loc.add(L);
        }
        return;
    }

    public void writeOut(String fileName) {
        String s = "";
        for(int i = 0; i < loc.size(); i++) {
            s += loc.get(i).addr + "," + loc.get(i).haz + "," + loc.get(i).desc + "##";
        }
        writeOutHelp(s, fileName);
    }

    public location sendLoc(int id) {
        try {
            return loc.get(id - 1);
        } catch (Exception e){
            Log.e("fileIO","index most likely out of bounds");
        }
    }

    public void writeOutHelp(String s, String fileName) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStreamWriter.write(s);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("FileIO", "File write failed: " + e.toString());
        }
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
        startActivity(intent);
    }

    // Called when the add button is pressed
    public void goToAdd(View view) {
        Intent intent = new Intent(this, addActivity.class);
        startActivity(intent);
    }
}
