package com.example.android_project.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class AsyncGetSpecific extends AsyncTask<String, Void, JSONObject> {
    @Override
    protected JSONObject doInBackground(String... strings) {
        final JSONObject[] response = {null};
        if (strings[0].equals("games") || strings[0].equals("teams") || strings[0].equals("players")) {
            AsyncHttpClient client = new AsyncHttpClient();
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

                // ----New Overridden method
                @Override
                public boolean getUseSynchronousMode() {
                    return false;
                }
            });
        } else {
            Log.e("ASYNC", "Wrong parameters must be ( players/games/teams, id )");
        }
        while (response[0] == null) ; // We wait for response
        return response[0];
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        Log.i("ASYNC", jsonObject.toString());
    }

    protected String buildUrl(String[] strings) {
        return "https://free-nba.p.rapidapi.com/" + strings[0] + "/" + strings[1];
    }
}
