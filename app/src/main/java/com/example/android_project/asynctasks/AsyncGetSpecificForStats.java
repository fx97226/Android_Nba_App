package com.example.android_project.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.android_project.adaptor.Stats_Adapter_Table;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class AsyncGetSpecificForStats extends AsyncTask<String, Void, JSONObject> {
    private final Stats_Adapter_Table adapter;

    public AsyncGetSpecificForStats(Stats_Adapter_Table adapter) {
        this.adapter = adapter;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        final JSONObject[] response = {null};
        if (strings[0].equals("games") || strings[0].equals("stats") || strings[0].equals("players")) {
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
            while (response[0] == null) ; // We wait for response
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
            for (int i = 0; i < data.length(); i++) {
                adapter.add(data.getJSONObject(i));
                adapter.UpdateRows(i);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    protected String buildUrl(String[] strings) {
        /*  */
        return "https://free-nba.p.rapidapi.com/" + strings[0] + "?page=0&per_page=25";
    }
}
