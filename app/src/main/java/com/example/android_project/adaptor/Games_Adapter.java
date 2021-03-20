package com.example.android_project.adaptor;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.android_project.Activity.NBA_Home;
import com.example.android_project.Fragment.StatsFragment;
import com.example.android_project.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Games_Adapter extends BaseAdapter {
    private final Context context; //context
    private ArrayList<JSONObject> items; //data source of the list adapter
    //private StatsFragment fragment = null; //Fragment launch when the card is clicked

    // CONSTRUCTOR
    public Games_Adapter(Context context) {
        this.context = context;
        items = new ArrayList<JSONObject>();
    }

    // ADD FUNCTION FOR THE LIST
    public void add(JSONObject jsonObject) {
        this.items.add(jsonObject);
    }


    public void clear() {
        this.items = new ArrayList<>();
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
        JSONObject game = (JSONObject) getItem(position);
        boolean data = true;

        // Verify Data
        try {
            if (game.getString("data").equals("No Games")) {
                data = false;
            }
        } catch (JSONException e) {
            // If error catch means that we have data so data can stay true
        }

        /**  If data we build our Games Card */

        if (data) {
            /* We create the display of our row like in our card_layout **/
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.card_layout, parent, false);
            }

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

                // Verification because our Image can't have the same name
                if (visitor_team.equals("76ers")) {
                    image_team2.setImageResource(context.getResources().getIdentifier("com.example.android_project:drawable/seventysixers", null, null));
                }
                if (visitor_team.equals("Trail Blazers")) {
                    image_team2.setImageResource(context.getResources().getIdentifier("com.example.android_project:drawable/blazers", null, null));
                } else {
                    image_team2.setImageResource(context.getResources().getIdentifier("com.example.android_project:drawable/" + game.getJSONObject("visitor_team").getString("name").toLowerCase(), null, null));
                }
                if (home_team.equals("76ers")) {
                    image_team1.setImageResource(context.getResources().getIdentifier("com.example.android_project:drawable/seventysixers", null, null));
                }
                if (home_team.equals("Trail Blazers")) {
                    image_team2.setImageResource(context.getResources().getIdentifier("com.example.android_project:drawable/blazers", null, null));
                } else {
                    image_team1.setImageResource(context.getResources().getIdentifier("com.example.android_project:drawable/" + game.getJSONObject("home_team").getString("name").toLowerCase(), null, null));
                }

                // We fill our TextView with the Data from the API
                name_team1.setText(game.getJSONObject("home_team").getString("name"));
                name_team2.setText(game.getJSONObject("visitor_team").getString("name"));
                card_date.setText(game.getString("date").substring(0, 10));
                score_team1.setText(game.getString("home_team_score"));
                score_team2.setText(game.getString("visitor_team_score"));


                // Add OnClickListener for each Card to open Statistic from this specific game
                convertView.findViewById(R.id.card_main).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            // We update our Navigation view ( Our bottom menu )
                            View rootView = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
                            BottomNavigationView bottomNavigationView = rootView.findViewById(R.id.bottom_nav_id);
                            bottomNavigationView.setSelectedItemId(R.id.statistic_id);
                            //Store the game_id in our NBA_Home
                            NBA_Home.game_id = game.getString("id");
                            ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment, StatsFragment.newInstance(game.getString("id")),  "StatsFragment").commit();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            } catch (JSONException e) {
                Log.e("JSON", "Object not found");
            }
        } else {
            //If no data we just inflate Our Empty Data Card !
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.empty_data, parent, false);
            }
        }

        return convertView;
    }
}