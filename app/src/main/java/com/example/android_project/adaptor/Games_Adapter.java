package com.example.android_project.adaptor;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.android_project.R;
import com.example.android_project.singleton.MySingleton;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        //Retrieve information
        try {
            date = game.getString("date");
            home_team = game.getJSONObject("home_team").getString("name");
            home_team_score = game.getString("home_team_score");
            visitor_team = game.getJSONObject("visitor_team").getString("name");
            visitor_team_score = game.getString("visitor_team_score");
        } catch (JSONException e) {
            Log.e("JSON", "Object not found");
        }
        //Find view asset
        ImageView image_team1 = (ImageView) convertView.findViewById(R.id.image_team1);
        ImageView image_team2 = (ImageView) convertView.findViewById(R.id.image_team2);
        TextView name_team1 = (TextView) convertView.findViewById(R.id.name_team1);
        TextView name_team2 = (TextView) convertView.findViewById(R.id.name_team2);
        TextView score_team1 = (TextView) convertView.findViewById(R.id.score_team1);
        TextView score_team2 = (TextView) convertView.findViewById(R.id.score_team2);
        TextView card_date = (TextView) convertView.findViewById(R.id.card_date);

        Log.i("ASYNC" , home_team);
        assert home_team != null;
        Log.i("ASYNC" , home_team.toLowerCase());
        assert visitor_team != null;
        Log.i("ASYNC" , visitor_team.toLowerCase());

        image_team1.setImageResource(context.getResources().getIdentifier("com.example.android_project:drawable/" + home_team.toLowerCase(), null, null));
        image_team2.setImageResource(context.getResources().getIdentifier("com.example.android_project:drawable/" + visitor_team.toLowerCase(), null, null));
        name_team1.setText(home_team);
        name_team2.setText(visitor_team);
        card_date.setText(date.substring(0, 10));
        score_team1.setText(home_team_score);
        score_team2.setText(visitor_team_score);

        try {
            Log.i("ASYNC", String.valueOf(game.getJSONArray("id")));
        } catch (JSONException e) {
            e.printStackTrace();
        }

       /* //Create our RequestQueue instance
        RequestQueue queue = MySingleton.getInstance(image.getContext()).getRequestQueue();
        //Initialise a new ImageRequest
        ImageRequest request = new ImageRequest(getItem(position).toString(),
                (Response.Listener<Bitmap>) image::setImageBitmap, // When our response is catch we display the internal image saved to our image view
                200,
                200,
                ImageView.ScaleType.CENTER,
                Bitmap.Config.RGB_565,
                new Response.ErrorListener() {
                    //If we catch an error response
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("JFL", "Image Load Error: ");
                    }
                });
        //Add Image request to the RequestQueue
        queue.add(request);
        // returns the view for the current row*/
        return convertView;
    }
}