package com.example.animationdemo.animationdemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // creates an options menu for the action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // The ObjectAnimator class offers the easiest way to animate an object, most probably a View, by continually updating one of its properties. To create an animation, create an ObjectAnimator using one of its factory methods, passing a target object, a property name, and the start and end values for the property. In recognition of the fact that a property can have an int value, a float value, or another type of value, ObjectAnimator provides three static methods: ofInt, ofFloat, and ofObject. Here are their signatures.
    // this animation rotates the image on it's Y-axis twice
    public void animate1(View source) {
        View view = findViewById(R.id.imageView1);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "rotationY", 0F, 720.0F);
        objectAnimator.setDuration(2000);
        objectAnimator.start();
    }

    // A ValueAnimator creates an animation by calculating a value that transitions from a start value and to an end value. You specify what the start value and end value should be when constructing the ValueAnimator. By registering an UpdateListener to a ValueAnimator, you can receive an update at each frame, giving you a chance to update your object(s).
    // this animation
    public void animate2(View source) {
        final View view = findViewById(R.id.imageView1);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0F, 720.0F);
        valueAnimator.setDuration(15000);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                view.setRotationX(value);
                if (value < 3600) {
                    view.setTranslationX(value/20);
                    view.setTranslationY(value/20);
                }

                else {
                    view.setTranslationX((7200-value)/20);
                    view.setTranslationY((7200-value)/20);
                }
            }
        });
        valueAnimator.start();
    }

    // An AnimatorSet is useful if you want to play a set of animations in a certain order. A direct subclass of Animator, the AnimatorSet class allows you to play multiple animations together or one after another. Once you’re finished deciding how your animations should be called, call the start method on the AnimatorSet to start it.
    // this animation
    public void animate3(View source) {
        View view = findViewById(R.id.imageView1);

        // this creates an ObjectAnimator object named objectAnimator1 which moves imageView1 along the Y-axis at 300 pixels to the bottom
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(view, "translationY", 0F, 300.0F);
        // this sets an animation duration of 2000 ms for objectAnimator1
        objectAnimator1.setDuration(2000);
        // this creates an ObjectAnimator object named objectAnimator2 which moves imageView1 along the X-axis at 300 pixels to the right
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(view, "translationX", 0F, 300.0F);
        // this sets an animation duration of 2000 ms for objectAnimator2
        objectAnimator2.setDuration(2000);

        // this creates an AnimatorSet object named animatorSet
        AnimatorSet animatorSet = new AnimatorSet();
        // this line of code executes animations (objectAnimator1 & objectAnimator2) at the same time
        animatorSet.playTogether(objectAnimator1, objectAnimator2);


        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(view, "rotation", 0F, 144.0F);
        // this sets an animation duration of 4000 ms for objectAnimator3
        objectAnimator3.setDuration(4000);
        animatorSet.play(objectAnimator3).after(objectAnimator2);
        animatorSet.start();
    }
}
