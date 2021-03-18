package com.example.android_project.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.android_project.R;

public class MainActivity extends AppCompatActivity {

    public static int SPLASH_TIME= 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, NBA_login.class);
                startActivity(i);
                finish();
            }
        }, 4*1000); // wait for 4 seconds
    }

}