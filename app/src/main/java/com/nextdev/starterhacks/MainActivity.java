package com.nextdev.starterhacks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GestureDetectorCompat gestureObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createShortcut();
        fileIO io = new fileIO();
        ArrayList<location> locList = io.readIn();

        gestureObject = new GestureDetectorCompat(this, new SwipeGesture());
        // SORTING TO BE ENABLED ONCE GOOGLE MAPS API
        // sortLocations sLoc = new sortLocations();
        // locList = sort(sLoc);

        location[] top4 = new location[4];

        //for (int i = 0; i < 4; i++) {
        //    top4[i] = locList.get(i);
        //}

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
        startActivity(intent);
    }

    // Called when the add button is pressed
    public void goToAdd(View view) {
        Intent intent = new Intent(this, addActivity.class);
        startActivity(intent);
    }
}
