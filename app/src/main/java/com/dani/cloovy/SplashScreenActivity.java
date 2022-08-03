package com.dani.cloovy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {

    private final String TAG = SplashScreenActivity.class.getSimpleName();

    private MyPreferences myPreferences;
    private final int TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        myPreferences = new MyPreferences(SplashScreenActivity.this);
        new Handler().postDelayed(() -> {
            if (MyPreferences.getSharedPreferences().
                    getBoolean(MyPreferences.IS_LOGIN,false)){
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }else{
                Intent intent = new Intent(SplashScreenActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }

        }, TIME_OUT);
    }
}