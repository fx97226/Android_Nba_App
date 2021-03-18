package com.example.android_project.adaptor;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.android_project.R;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class Stats_Adapter_Table {
    private Context context; //context
    private final ArrayList<JSONObject> items; //data source of the list adapter

    public Stats_Adapter_Table(Context context) {
        this.context = context;
        items = new ArrayList<>();
    }

    // ADD FUNCTION FOR THE LIST
    public void add(JSONObject string) {
        Log.i("SAVE", String.valueOf(string));
        this.items.add(string);
    }
    public int getCount() {
        return items.size();
    }
    public Object getItem(int position) {
        return items.get(position);
    }

    public void RestoreState(Context context) {
        this.context = context;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                for( int i = 0; i < getCount() ; i++){
                    UpdateRows(i);
                }
            }
        }, 2000);//Wait 2 seconds
    }

    public void UpdateRows(int position){
        View rootView = ((Activity)context).getWindow().getDecorView().findViewById(android.R.id.content);
        TableLayout table = (TableLayout) rootView.findViewById(R.id.tblData);
        TableRow tableRow = (TableRow) ((Activity)context).getLayoutInflater().inflate(R.layout.fragment_stats_row, null);
        TextView tv;
        JSONObject stats = (JSONObject) getItem(position);
        try{
            tv = (TextView) tableRow.findViewById(R.id.cell_1);
            String input  = stats.getJSONObject("player").getString("first_name") + " " + stats.getJSONObject("player").getString("last_name");
            Log.i("ASYNC", " player : " + input);
            tv.setText(input);
            tv = (TextView) tableRow.findViewById(R.id.cell_2);
            Log.i("ASYNC", " pts : " + String.valueOf(stats.getString("pts")));
            tv.setText(stats.getString("pts"));
            tv = (TextView) tableRow.findViewById(R.id.cell_3);
            Log.i("ASYNC", " reb : " + String.valueOf(stats.getString("reb")));
            tv.setText(stats.getString("reb"));
            tv = (TextView) tableRow.findViewById(R.id.cell_4);
            Log.i("ASYNC", " stl : " + String.valueOf(stats.getString("stl")));
            tv.setText(stats.getString("stl"));
            tv = (TextView) tableRow.findViewById(R.id.cell_5);
            Log.i("ASYNC", " turn : " + String.valueOf(stats.getString("turnover")));
            tv.setText(stats.getString("turnover"));
            tv = (TextView) tableRow.findViewById(R.id.cell_6);
            Log.i("ASYNC", " min : " + String.valueOf(stats.getString("min")));
            tv.setText(stats.getString("min"));
            table.addView(tableRow);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
