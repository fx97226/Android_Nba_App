package com.example.android_project;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.android_project.adaptor.Games_Adapter;
import com.example.android_project.asynctasks.AsyncGetSpecific;
public class GamesFragment extends Fragment {

    private Games_Adapter game_adapter = null;
    private Boolean loaded = false;

    private Bundle savedState = new Bundle();
    public GamesFragment() {
        // Required empty public constructor
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null){
            loaded = savedInstanceState.getBoolean("loaded");
            Log.i("SAVE", "1 : Loaded is " + loaded);
        }
        Log.i("SAVE", "2 : Loaded is " + loaded);
        Log.i("SAVE", "2 : SaveInstance is " + savedInstanceState);
        savedState = savedInstanceState;
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("SAVE", "On save Instance de GamesFragment");
        outState.putBoolean("loaded", true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Creation de la liste et lien avec l'adapter

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_games, container, false);
        Log.i("FRAG", "Je suis dans le GamesFragment");
        game_adapter = new Games_Adapter(this.getActivity());
        ListView list = (ListView) view.findViewById(R.id.list);
        list.setAdapter(game_adapter);
        AsyncGetSpecific asyncTask = new AsyncGetSpecific(game_adapter);
        asyncTask.execute("games");
        // Inflate the layout for this fragment
        //CardView main_card = (CardView) view.findViewById(R.id.card_main);
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        savedState = new Bundle();
        onSaveInstanceState(savedState);
        Log.i("SAVE", "ON destroy");
    }
}