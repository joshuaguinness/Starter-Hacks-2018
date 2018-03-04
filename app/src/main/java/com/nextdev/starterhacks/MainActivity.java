package com.nextdev.starterhacks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.View;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private GestureDetectorCompat gestureObject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = (Button)findViewById(R.id.addButton);

        gestureObject = new GestureDetectorCompat(this, new SwipeGesture());

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                android.support.v4.app.FragmentManager fragie = getSupportFragmentManager();
                fragie.beginTransaction().replace(R.id.frag1,new ReducedInfoFragment()).commit();
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureObject.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public void addMessage (View view) {
        /* this is to activate the add fragment */
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
            }
            return true;
        }
    }
}
