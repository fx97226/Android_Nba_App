package com.example.android_project.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.android_project.adaptor.Games_Adapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class AsyncGetAll extends AsyncTask<String, Void, JSONObject> {
    private final Games_Adapter adapter;

    public AsyncGetAll(Games_Adapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        final JSONObject[] response = {null};
        Log.i("ASYNC", " DoInBackground");
        if (strings[0].equals("games") || strings[0].equals("teams") || strings[0].equals("players")) {
            SyncHttpClient client = new SyncHttpClient();
            client.addHeader("x-rapidapi-key", "987a652a7cmshb9247e1fe068886p1ef584jsn4df5ed246ff7");
            client.addHeader("x-rapidapi-host", "free-nba.p.rapidapi.com");
            String url = buildUrl(strings);
            client.get(url, new JsonHttpResponseHandler() {
                public void onSuccess(int statusCode, Header[] headers, JSONObject json) {
                    response[0] = json;
                }

                public void onFailure(int statusCode, Header[] headers, Throwable t, JSONObject e) {
                    // Handle the failure and alert the user to retry
                    Log.e("ERROR", e.toString());
                    response[0] = e;
                }
            });
            while (response[0] == null) Log.i("ASYNC", "Waiting..."); // We wait for response
        } else {
            Log.e("ASYNC", "Wrong parameters must be ( players/games/teams, id )");
        }
        return response[0];
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        Log.i("ASYNC", jsonObject.toString());
        try {
            JSONArray data = jsonObject.getJSONArray("data");
            if(data.length() == 0){
                adapter.add(new JSONObject("{\"data\": \"No Games\"}"));
                Log.i("ASYNC", new JSONObject("{\"data\":\"No Games\"}").toString());
                adapter.notifyDataSetChanged();
            }else{
                for (int i = 0; i < data.length(); i++) {
                    adapter.add(data.getJSONObject(i));
                    adapter.notifyDataSetChanged();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected String buildUrl(String[] strings) {
        switch (strings.length){
            case 1:
                return "https://free-nba.p.rapidapi.com/" + strings[0];
            case 2:
                if(!strings[0].equals("players"))return "https://free-nba.p.rapidapi.com/" + strings[0] + "?dates[]=" + strings[1];
                return "https://free-nba.p.rapidapi.com/" + strings[0];
            default:
                return "https://free-nba.p.rapidapi.com/games";
        }
    }
}
