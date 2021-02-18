package com.example.android_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NBA_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_b_a_login);
        getSupportActionBar().setTitle("Login");
    }

    public void btn_register(View view) {
        startActivity(new Intent(getApplicationContext(),NBA_Register.class));
    }
}