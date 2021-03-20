package com.example.android_project.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.android_project.R;
import com.example.android_project.asynctasks.AsyncGetSpecificTeam;

public class NBA_Team_stat extends AppCompatActivity {
    AsyncGetSpecificTeam async = new AsyncGetSpecificTeam(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_b_a__team_stat);
        String TeamID = getIntent().getStringExtra("TEAM_ID");
        TeamID = TeamID.split("/")[1].split("_")[1];
        async.execute(TeamID);
    }
}