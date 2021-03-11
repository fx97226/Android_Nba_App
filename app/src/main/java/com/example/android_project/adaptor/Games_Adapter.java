package com.example.android_project.adaptor;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.android_project.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Games_Adapter extends BaseAdapter {
    private final Context context; //context
    private final ArrayList<JSONObject> items; //data source of the list adapter

    // CONSTRUCTOR
    public Games_Adapter(Context context) {
        this.context = context;
        items = new ArrayList<JSONObject>();
    }

    // ADD FUNCTION FOR THE LIST
    public void add(JSONObject string) {
        this.items.add(string);
    }

    // BASE ADAPTER METHOD
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /* We create the display of our row like in our card_layout **/
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.card_layout, parent, false);
        }
        JSONObject game = (JSONObject) getItem(position);
        String date = null, home_team = null, home_team_score = null, visitor_team = null, visitor_team_score = null;

        //Find view asset
        ImageView image_team1 = (ImageView) convertView.findViewById(R.id.image_team1);
        ImageView image_team2 = (ImageView) convertView.findViewById(R.id.image_team2);
        TextView name_team1 = (TextView) convertView.findViewById(R.id.name_team1);
        TextView name_team2 = (TextView) convertView.findViewById(R.id.name_team2);
        TextView score_team1 = (TextView) convertView.findViewById(R.id.score_team1);
        TextView score_team2 = (TextView) convertView.findViewById(R.id.score_team2);
        TextView card_date = (TextView) convertView.findViewById(R.id.card_date);

        //Retrieve information
        try {
            home_team = game.getJSONObject("home_team").getString("name");
            visitor_team = game.getJSONObject("visitor_team").getString("name");

            if (visitor_team.equals("76ers")) {
                Log.i("ASYNC", "going to sixers : " + visitor_team.toLowerCase());
                image_team2.setImageResource(context.getResources().getIdentifier("com.example.android_project:drawable/seventysixers", null, null));
            } else {
                image_team2.setImageResource(context.getResources().getIdentifier("com.example.android_project:drawable/" + game.getJSONObject("visitor_team").getString("name").toLowerCase(), null, null));
            }
            if (home_team.equals("76ers")) {
                Log.i("ASYNC", "going to sixers : " + home_team.toLowerCase());
                image_team1.setImageResource(context.getResources().getIdentifier("com.example.android_project:drawable/seventysixers", null, null));
            } else {
                image_team1.setImageResource(context.getResources().getIdentifier("com.example.android_project:drawable/" + game.getJSONObject("home_team").getString("name").toLowerCase(), null, null));
            }
            name_team1.setText(game.getJSONObject("home_team").getString("name"));
            name_team2.setText(game.getJSONObject("visitor_team").getString("name"));
            card_date.setText(game.getString("date").substring(0, 10));
            score_team1.setText(game.getString("home_team_score"));
            score_team2.setText(game.getString("visitor_team_score"));
        } catch (JSONException e) {
            Log.e("JSON", "Object not found");
        }
        Log.i("ASYNC", home_team);
        return convertView;
    }
}