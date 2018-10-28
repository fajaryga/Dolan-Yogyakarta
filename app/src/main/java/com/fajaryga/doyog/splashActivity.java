package com.fajaryga.doyog;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fajaryga.doyog.util.PreferencesHelper;

public class splashActivity extends AppCompatActivity {

    PreferencesHelper instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        instance = PreferencesHelper.getInstance(getApplicationContext());
        int splashInterval = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!instance.isLogin()) {
                    startActivity(new Intent(splashActivity.this, LoginActivity.class));
                } else {
                    startActivity(new Intent(splashActivity.this, MainActivity.class));
                }
            }
        },splashInterval);
    }
}