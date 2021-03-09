package com.example.android_project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.android_project.adaptor.Games_Adapter;
import com.example.android_project.asynctasks.AsyncGetSpecific;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GamesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GamesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Games_Adapter game_adapter = null;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GamesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GamesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GamesFragment newInstance(String param1, String param2) {
        GamesFragment fragment = new GamesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Creation de la liste et lien avec l'adapter

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_games, container, false);
        //CardView main_card = (CardView) view.findViewById(R.id.card_main);

        game_adapter = new Games_Adapter(this.getActivity());
        ListView list = (ListView) view.findViewById(R.id.list);
        list.setAdapter(game_adapter);

        AsyncGetSpecific asyncTask = new AsyncGetSpecific(game_adapter);
        asyncTask.execute("games");

        // Inflate the layout for this fragment
        //CardView main_card = (CardView) view.findViewById(R.id.card_main);
        return view;
    }
}