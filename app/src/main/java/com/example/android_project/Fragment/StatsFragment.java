package com.example.android_project.Fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_project.R;
import com.example.android_project.adaptor.Stats_Adapter_Table;
import com.example.android_project.asynctasks.AsyncGetSpecificForStats;

public class StatsFragment extends Fragment {
    private Stats_Adapter_Table stats_adapter;
    private String game_id = null;

    public StatsFragment(String game_id) {
        this.game_id = game_id;
    }

    public void launchStats(String game_id){
        this.game_id = game_id;
        this.stats_adapter.Clear();
        AsyncGetSpecificForStats asyncTask = new AsyncGetSpecificForStats(stats_adapter);
        Log.i("ASYNC", "Game is : " + game_id);
        asyncTask.execute("stats" , game_id);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Statistic");
        Log.i("SAVE", "Create View Stats");
        if (stats_adapter == null) {
            Log.i("ASYNC", "Stats_adapter == null");
            stats_adapter = new Stats_Adapter_Table(this.getActivity());
        }
        if (stats_adapter.getCount() > 0) {
            stats_adapter.RestoreState(this.getActivity());
        } else {
            AsyncGetSpecificForStats asyncTask = new AsyncGetSpecificForStats(stats_adapter);
            Log.i("ASYNC", "Game is : " + game_id);
            asyncTask.execute("stats" , game_id);
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats_temp, container, false);
    }
}