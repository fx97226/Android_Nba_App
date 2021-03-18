package com.example.android_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;

import com.example.android_project.asynctasks.AsyncGetSpecific;
import com.example.android_project.asynctasks.AsyncGetSpecificTeam;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cz.msebera.android.httpclient.Header;

public class NBA_Team_stat extends AppCompatActivity {
    AsyncGetSpecificTeam async = new AsyncGetSpecificTeam(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_b_a__team_stat);
        String TeamID = getIntent().getStringExtra("NAME_TEAM");
        TeamID = TeamID.split("/")[1].split("_")[1];
        if(async.getStatus() == AsyncTask.Status.PENDING){
            Log.i("ASYNC", "My AsyncTask has not started yet");
        }

        if(async.getStatus() == AsyncTask.Status.RUNNING){
            Log.i("ASYNC", "My AsyncTask is currently doing work in doInBackground()");
        }

        if(async.getStatus() == AsyncTask.Status.FINISHED){
            Log.i("ASYNC", " My AsyncTask is done and onPostExecute was called");
        }
        Log.i("ASYNC", String.valueOf(async.getStatus()));
        Log.i("TEAM", "Session Id : " + TeamID);
        async.execute(TeamID);
    }







}