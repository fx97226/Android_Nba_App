package com.example.android_project.asynctasks;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.example.android_project.R;
import com.example.android_project.adaptor.Games_Adapter;
import com.example.android_project.adaptor.Stats_Adapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class AsyncGetSpecificForStats extends AsyncTask<String, Void, JSONObject> {
    private final Context myActivity;

    public AsyncGetSpecificForStats(Context myActivity) {
        this.myActivity = myActivity;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        final JSONObject[] response = {null};
        if (strings[0].equals("games") || strings[0].equals("stats") || strings[0].equals("players")) {
            AsyncHttpClient client = new AsyncHttpClient();
            client.addHeader("x-rapidapi-key", "987a652a7cmshb9247e1fe068886p1ef584jsn4df5ed246ff7");
            client.addHeader("x-rapidapi-host", "free-nba.p.rapidapi.com");
            for (int i = 0; i < 10; i++) {
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
            }
        } else {
            Log.e("ASYNC", "Wrong parameters must be ( players/games/teams, id )");
        }
        while (response[0] == null) ; // We wait for response
        return response[0];
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        Log.i("ASYNC", jsonObject.toString());
        View rootView = ((Activity)myActivity).getWindow().getDecorView().findViewById(android.R.id.content);
        try {
            JSONArray data = jsonObject.getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {
                TableLayout table = (TableLayout) rootView.findViewById(R.id.tblData);
                TableRow tableRow = (TableRow) ((Activity)myActivity).getLayoutInflater().inflate(R.layout.fragment_stats_row, null);
                TextView tv;
                Log.i("ASYNC", " i : " + String.valueOf(data.getJSONObject(i).getString("id")));
                tv = (TextView) tableRow.findViewById(R.id.cell_1);
                String input  = data.getJSONObject(i).getJSONObject("player").getString("first_name") + " " + data.getJSONObject(i).getJSONObject("player").getString("last_name");
                Log.i("ASYNC", " player : " + input);
                tv.setText(input);
                tv = (TextView) tableRow.findViewById(R.id.cell_2);
                Log.i("ASYNC", " pts : " + String.valueOf(data.getJSONObject(i).getString("pts")));
                tv.setText(data.getJSONObject(i).getString("pts"));
                tv = (TextView) tableRow.findViewById(R.id.cell_3);
                Log.i("ASYNC", " reb : " + String.valueOf(data.getJSONObject(i).getString("reb")));
                tv.setText(data.getJSONObject(i).getString("reb"));
                tv = (TextView) tableRow.findViewById(R.id.cell_4);
                Log.i("ASYNC", " stl : " + String.valueOf(data.getJSONObject(i).getString("stl")));
                tv.setText(data.getJSONObject(i).getString("stl"));
                tv = (TextView) tableRow.findViewById(R.id.cell_5);
                Log.i("ASYNC", " turn : " + String.valueOf(data.getJSONObject(i).getString("turnover")));
                tv.setText(data.getJSONObject(i).getString("turnover"));
                tv = (TextView) tableRow.findViewById(R.id.cell_6);
                Log.i("ASYNC", " min : " + String.valueOf(data.getJSONObject(i).getString("min")));
                tv.setText(data.getJSONObject(i).getString("min"));
                table.addView(tableRow);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    protected String buildUrl(String[] strings) {
        /*  */
        return "https://free-nba.p.rapidapi.com/" + strings[0] + "?page=0&per_page=25" ;
    }
}
