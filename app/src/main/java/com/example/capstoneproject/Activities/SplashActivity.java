package com.example.capstoneproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.window.SplashScreen;

import com.example.capstoneproject.R;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    private boolean isFirstAnimation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Animation hold = AnimationUtils.loadAnimation(this, R.anim.hold);
        final Animation translateScale = AnimationUtils.loadAnimation(this, R.anim.translate_scale);
        final ImageView imageView = findViewById(R.id.header_icon);

        SharedPreferences myPrefs = this.getSharedPreferences("Login", MODE_PRIVATE);
        String username = myPrefs.getString("email", null);
        String password = myPrefs.getString("password", null);

        translateScale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!isFirstAnimation) {
                    imageView.clearAnimation();
                    if (username != null && password != null)
                        startAnotherActivity(MainActivity.class);
                    else
                        startAnotherActivity(CredentialsActivity.class);
                }
                isFirstAnimation = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        
        hold.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.clearAnimation();
                imageView.startAnimation(translateScale);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        imageView.startAnimation(hold);
    }

    public void startAnotherActivity(Class aClass) {
        Intent intent = new Intent(SplashActivity.this, aClass);
        startActivity(intent);
        finish();
    }

}