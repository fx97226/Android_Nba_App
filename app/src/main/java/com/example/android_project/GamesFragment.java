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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GamesFragment extends Fragment {

    private Games_Adapter game_adapter;
    private ArrayList<String> game_adapter_SaveState;

    private Boolean loaded = false;
    private Bundle savedState = new Bundle();

    public GamesFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*Log.i("SAVE", "On save Instance de GamesFragment");
        if (savedInstanceState != null && savedInstanceState.containsKey("KEY_ADAPTER_STATE")) {
            Log.i("SAVE", "Je recup la sauvegarde");
            game_adapter_SaveState = savedInstanceState.getStringArrayList("KEY_ADAPTER_STATE");
        }else{
            Log.i("SAVE", "Nothing save yet");
        }*/
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        /*if( game_adapter != null){
            game_adapter_SaveState = game_adapter.onSaveInstanceState();
        }
        outState.putStringArrayList("KEY_ADAPTER_STATE", game_adapter_SaveState);
        Log.i("SAVE", "On save Instance de GamesFragment");*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Creation de la liste et lien avec l'adapter
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_games, container, false);
        ListView list = (ListView) view.findViewById(R.id.list);
        Log.i("FRAG", "Je suis dans le GamesFragment");
        if(game_adapter == null){
            game_adapter = new Games_Adapter(this.getActivity());
        }
        if(game_adapter_SaveState != null){
            try {
                game_adapter.onRestoreInstanceState(game_adapter_SaveState);
                list.setAdapter(game_adapter);
                game_adapter.notifyDataSetChanged();
                Log.i("SAVE", "Restoration complete");
            } catch (JSONException e) {
                e.printStackTrace();
                Log.i("SAVE", "ERROR in restore");
            }
        }else{
            list.setAdapter(game_adapter);
            Log.i("SAVE", "Launch ASYNC");
            AsyncGetSpecific asyncTask = new AsyncGetSpecific(game_adapter);
            asyncTask.execute("games");
        }
        // Inflate the layout for this fragment
        //CardView main_card = (CardView) view.findViewById(R.id.card_main);
        return view;
    }
}