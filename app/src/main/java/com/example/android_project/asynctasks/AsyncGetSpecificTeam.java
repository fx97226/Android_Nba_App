package com.example.android_project.asynctasks;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_project.R;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class AsyncGetSpecificTeam extends AsyncTask<String, Void, JSONObject> {
    private final Context myActivity;

    public AsyncGetSpecificTeam(Context myActivity) {
        this.myActivity = myActivity;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        final JSONObject[] response = {null};
        SyncHttpClient client = new SyncHttpClient();
        // Add our Key ( in clear)  for the API
        // not safe if the API is private but here no problem
        client.addHeader("x-rapidapi-key", "987a652a7cmshb9247e1fe068886p1ef584jsn4df5ed246ff7");
        client.addHeader("x-rapidapi-host", "free-nba.p.rapidapi.com");
        String url = buildUrl(strings);
        // Launch our request
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
        while (response[0] == null) ; // We wait for response
        return response[0];
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        // Retrieve the activity and update all textViews and images
        Activity activity = (Activity) myActivity;
        ImageView image_team1 = (ImageView) activity.findViewById(R.id.img);
        TextView full_name = (TextView) activity.findViewById(R.id.full_name);
        TextView city = (TextView) activity.findViewById(R.id.city);
        TextView number = (TextView) activity.findViewById(R.id.id);
        TextView abbre = (TextView) activity.findViewById(R.id.abbre);
        TextView conference = (TextView) activity.findViewById(R.id.conference);
        try {
            String team_name = jsonObject.getString("name");
            if (team_name.equals("76ers")) {
                image_team1.setImageResource(myActivity.getResources().getIdentifier("com.example.android_project:drawable/seventysixers", null, null));
            }
            if (team_name.equals("Trail Blazers")) {
                image_team1.setImageResource(myActivity.getResources().getIdentifier("com.example.android_project:drawable/blazers", null, null));
            } else {
                image_team1.setImageResource(myActivity.getResources().getIdentifier("com.example.android_project:drawable/" + jsonObject.getString("name").toLowerCase(), null, null));
            }
            full_name.setText(jsonObject.getString("full_name"));
            city.setText(jsonObject.getString("city"));
            number.setText(jsonObject.getString("id"));
            abbre.setText(jsonObject.getString("abbreviation"));
            conference.setText(jsonObject.getString("conference"));
        } catch (JSONException e) {
            Log.e("JSON", "Object not found");
            e.printStackTrace();
        }
    }

    protected String buildUrl(String[] strings) {
        return "https://free-nba.p.rapidapi.com/teams/" + strings[0];
    }
}
