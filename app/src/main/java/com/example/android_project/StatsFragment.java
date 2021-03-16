package com.example.android_project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_project.adaptor.Stats_Adapter_Table;
import com.example.android_project.asynctasks.AsyncGetSpecificForStats;

public class StatsFragment extends Fragment {
    private Stats_Adapter_Table stats_adapter;

    public StatsFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("SAVE", "Create View Stats");
        if(stats_adapter == null){
            stats_adapter = new Stats_Adapter_Table(this.getActivity());
        }
        if(stats_adapter.getCount() > 0){
            stats_adapter.RestoreState(this.getActivity());
        }else{
            AsyncGetSpecificForStats asyncTask = new AsyncGetSpecificForStats(stats_adapter);
            asyncTask.execute("stats");
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats_temp, container, false);
    }
}