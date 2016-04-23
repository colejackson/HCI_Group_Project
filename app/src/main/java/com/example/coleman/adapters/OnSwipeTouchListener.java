package com.example.coleman.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.coleman.app_code.Todo;
import com.example.coleman.hcigroupproject.Main;
import com.example.coleman.hcigroupproject.R;
import com.example.coleman.xml.DataParser;

public class OnSwipeTouchListener implements OnTouchListener {

    private final GestureDetector gestureDetector;
    private final DataParser dp;
    private final Todo td;
    private final Context ctx;

    public OnSwipeTouchListener (Context ctx, DataParser dp, Todo td)
    {
        gestureDetector = new GestureDetector(ctx, new GestureListener());
        this.dp = dp;
        this.td = td;
        this.ctx = ctx;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {

        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 70;
        private static final int SWIPE_VELOCITY_THRESHOLD = 80;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                    }
                    result = true;
                }
                else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeBottom();
                    } else {
                        onSwipeTop();
                    }
                }
                result = true;

            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }
    }

    public void onSwipeRight()
    {
        dp.removeNote(td);
        ((Main)ctx).update();
    }

    public void onSwipeLeft() {
    }

    public void onSwipeTop() {
    }

    public void onSwipeBottom() {
    }
}
