package com.example.android_project.adaptor;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.android_project.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Stats_Adapter extends BaseAdapter {
    private final Context context; //context
    private final ArrayList<JSONObject> items; //data source of the list adapter

    // CONSTRUCTOR
    public Stats_Adapter(Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_stats_row, parent, false);
        }

        return convertView;
    }
}