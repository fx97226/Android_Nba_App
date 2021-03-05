package com.example.android_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.example.android_project.asynctasks.AsyncGetSpecific;
import com.example.android_project.asynctasks.AsyncGetSpecificGame;
import com.example.android_project.asynctasks.AsyncGetSpecificPlayer;
import com.example.android_project.asynctasks.AsyncGetSpecificTeam;

import org.json.JSONObject;

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

    public void btn_login(View view){
      startActivity(new Intent(getApplicationContext(),NBA_Home.class));

        AsyncTask<String, Void, JSONObject> task = new AsyncGetSpecificGame();
        task.execute("9");
        AsyncTask<String, Void, JSONObject> task2 = new AsyncGetSpecificPlayer();
        task2.execute("10");
        AsyncTask<String, Void, JSONObject> task3 = new AsyncGetSpecificTeam();
        task3.execute("11");

        AsyncTask<String, Void, JSONObject> task4 = new AsyncGetSpecific();
        task.execute("games", "10");
    }
}