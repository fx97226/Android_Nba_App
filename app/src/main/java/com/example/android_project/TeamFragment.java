package com.example.android_project;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TeamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public CardView cardView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TeamFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TeamFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TeamFragment newInstance(String param1, String param2) {
        TeamFragment fragment = new TeamFragment();
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

        View v = inflater.inflate(R.layout.fragment_team, container, false);
        CardView a = (CardView) v.findViewById(R.id.team_blazers);
        a.setOnClickListener(this);
        CardView b = (CardView) v.findViewById(R.id.team_bucks);
        b.setOnClickListener(this);
        CardView b1 = (CardView) v.findViewById(R.id.team_bulls);
        b1.setOnClickListener(this);
        CardView c = (CardView) v.findViewById(R.id.team_cavaliers);
        c.setOnClickListener(this);
        CardView c1 = (CardView) v.findViewById(R.id.team_celtics);
        c1.setOnClickListener(this);
        CardView c2 = (CardView) v.findViewById(R.id.team_clippers);
        c2.setOnClickListener(this);
        CardView d = (CardView) v.findViewById(R.id.team_grizzlies);
        d.setOnClickListener(this);
        CardView e = (CardView) v.findViewById(R.id.team_hawks);
        e.setOnClickListener(this);
        CardView f = (CardView) v.findViewById(R.id.team_heat);
        f.setOnClickListener(this);
        CardView g = (CardView) v.findViewById(R.id.team_hornets);
        g.setOnClickListener(this);
        CardView h = (CardView) v.findViewById(R.id.team_jazz);
        h.setOnClickListener(this);
        CardView i = (CardView) v.findViewById(R.id.team_kings);
        i.setOnClickListener(this);
        CardView j = (CardView) v.findViewById(R.id.team_knicks);
        j.setOnClickListener(this);
        CardView k = (CardView) v.findViewById(R.id.team_lakers);
        k.setOnClickListener(this);
        CardView l = (CardView) v.findViewById(R.id.team_magic);
        l.setOnClickListener(this);
        CardView m = (CardView) v.findViewById(R.id.team_mavericks);
        m.setOnClickListener(this);
        CardView n = (CardView) v.findViewById(R.id.team_nets);
        n.setOnClickListener(this);
        CardView o = (CardView) v.findViewById(R.id.team_nuggets);
        o.setOnClickListener(this);
        CardView p = (CardView) v.findViewById(R.id.team_pacers);
        p.setOnClickListener(this);
        CardView p1 = (CardView) v.findViewById(R.id.team_pelicans);
        p1.setOnClickListener(this);
        CardView p2 = (CardView) v.findViewById(R.id.team_pistons);
        p2.setOnClickListener(this);
        CardView q = (CardView) v.findViewById(R.id.team_raptors);
        q.setOnClickListener(this);
        CardView r = (CardView) v.findViewById(R.id.team_rockets);
        r.setOnClickListener(this);
        CardView s = (CardView) v.findViewById(R.id.team_spurs);
        s.setOnClickListener(this);
        CardView t = (CardView) v.findViewById(R.id.team_suns);
        t.setOnClickListener(this);
        CardView u = (CardView) v.findViewById(R.id.team_supersonic);
        u.setOnClickListener(this);
        CardView v1 = (CardView) v.findViewById(R.id.team_timberwolves);
        v1.setOnClickListener(this);
        CardView v2 = (CardView) v.findViewById(R.id.team_thunder);
        v2.setOnClickListener(this);
        CardView w = (CardView) v.findViewById(R.id.team_warriors);
        w.setOnClickListener(this);
        CardView x = (CardView) v.findViewById(R.id.team_wizards);
        x.setOnClickListener(this);
        CardView y = (CardView) v.findViewById(R.id.team_76ers);
        y.setOnClickListener(this);

        return v;
        // Inflate the layout for this fragment


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.team_blazers:
            case R.id.team_bulls:
            case R.id.team_cavaliers:
            case R.id.team_celtics:
            case R.id.team_clippers:
            case R.id.team_grizzlies:
            case R.id.team_hawks:
            case R.id.team_heat:
            case R.id.team_hornets:
            case R.id.team_jazz:
            case R.id.team_kings:
            case R.id.team_knicks:
            case R.id.team_lakers:
            case R.id.team_magic:
            case R.id.team_mavericks:
            case R.id.team_nets:
            case R.id.team_nuggets:
            case R.id.team_pacers:
            case R.id.team_pelicans:
            case R.id.team_pistons:
            case R.id.team_raptors:
            case R.id.team_rockets:
            case R.id.team_76ers:
            case R.id.team_spurs:
            case R.id.team_suns:
            case R.id.team_supersonic:
            case R.id.team_timberwolves:
            case R.id.team_thunder:
            case R.id.team_warriors:
            case R.id.team_wizards:
     case R.id.team_bucks:
                Intent intent = new Intent(getActivity(), NBA_Team_stat.class);
                startActivity(intent);
                break;
        }

    }
}
