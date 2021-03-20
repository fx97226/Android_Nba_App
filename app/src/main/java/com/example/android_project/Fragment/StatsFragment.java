package com.example.android_project.Fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_project.R;
import com.example.android_project.adaptor.Games_Adapter;
import com.example.android_project.adaptor.Stats_Adapter_Table;
import com.example.android_project.asynctasks.AsyncGetAll;
import com.example.android_project.asynctasks.AsyncGetSpecificForStats;

public class StatsFragment extends Fragment {
    private Stats_Adapter_Table stats_adapter; // Our specific tableRow adaptor
    private String game_id; // Game_id of the stats showed

    public StatsFragment() {
        // Empty Public Constructor for Fragment
    }

    //NewInstance to pass game_id information in the fragment
    public static StatsFragment newInstance(String param1) {
        StatsFragment fragment = new StatsFragment();
        Bundle args = new Bundle();
        args.putString("game_id", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            game_id = getArguments().getString("game_id");  // We retrieve game_id
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Statistic");
        if (this.game_id == null) {
            this.game_id = "127869"; // Security
        }
        // If we don't have an adaptor
        if (stats_adapter == null) {
            stats_adapter = new Stats_Adapter_Table(this.getActivity());

        }
        // We launch our asynctask to retrieve data from the API
        AsyncGetSpecificForStats asyncTask = new AsyncGetSpecificForStats(stats_adapter);
        asyncTask.execute("stats", game_id);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats_temp, container, false);
    }
}