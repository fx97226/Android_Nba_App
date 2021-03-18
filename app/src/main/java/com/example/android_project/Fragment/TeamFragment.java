package com.example.android_project.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_project.Activity.NBA_Team_stat;
import com.example.android_project.R;

public class TeamFragment extends Fragment implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Teams");
        View v = inflater.inflate(R.layout.fragment_team, container, false);
        CardView a = (CardView) v.findViewById(R.id.team_25);
        a.setOnClickListener(this);
        CardView b = (CardView) v.findViewById(R.id.team_17);
        b.setOnClickListener(this);
        CardView b1 = (CardView) v.findViewById(R.id.team_6);
        b1.setOnClickListener(this);
        CardView c = (CardView) v.findViewById(R.id.team_5);
        c.setOnClickListener(this);
        CardView c1 = (CardView) v.findViewById(R.id.team_13);
        c1.setOnClickListener(this);
        CardView c2 = (CardView) v.findViewById(R.id.team_2);
        c2.setOnClickListener(this);
        CardView d = (CardView) v.findViewById(R.id.team_1);
        d.setOnClickListener(this);
        CardView e = (CardView) v.findViewById(R.id.team_15);
        e.setOnClickListener(this);
        CardView f = (CardView) v.findViewById(R.id.team_16);
        f.setOnClickListener(this);
        CardView g = (CardView) v.findViewById(R.id.team_4);
        g.setOnClickListener(this);
        CardView h = (CardView) v.findViewById(R.id.team_26);
        h.setOnClickListener(this);
        CardView i = (CardView) v.findViewById(R.id.team_29);
        i.setOnClickListener(this);
        CardView j = (CardView) v.findViewById(R.id.team_14);
        j.setOnClickListener(this);
        CardView k = (CardView) v.findViewById(R.id.team_20);
        k.setOnClickListener(this);
        CardView l = (CardView) v.findViewById(R.id.team_7);
        l.setOnClickListener(this);
        CardView m = (CardView) v.findViewById(R.id.team_22);
        m.setOnClickListener(this);
        CardView n = (CardView) v.findViewById(R.id.team_8);
        n.setOnClickListener(this);
        CardView o = (CardView) v.findViewById(R.id.team_3);
        o.setOnClickListener(this);
        CardView p = (CardView) v.findViewById(R.id.team_19);
        p.setOnClickListener(this);
        CardView p1 = (CardView) v.findViewById(R.id.team_12);
        p1.setOnClickListener(this);
        CardView p2 = (CardView) v.findViewById(R.id.team_28);
        p2.setOnClickListener(this);
        CardView q = (CardView) v.findViewById(R.id.team_9);
        q.setOnClickListener(this);
        CardView r = (CardView) v.findViewById(R.id.team_23);
        r.setOnClickListener(this);
        CardView s = (CardView) v.findViewById(R.id.team_11);
        s.setOnClickListener(this);
        CardView t = (CardView) v.findViewById(R.id.team_24);
        t.setOnClickListener(this);
        CardView u = (CardView) v.findViewById(R.id.team_supersonic);
        u.setOnClickListener(this);
        CardView v1 = (CardView) v.findViewById(R.id.team_18);
        v1.setOnClickListener(this);
        CardView v2 = (CardView) v.findViewById(R.id.team_21);
        v2.setOnClickListener(this);
        CardView w = (CardView) v.findViewById(R.id.team_10);
        w.setOnClickListener(this);
        CardView x = (CardView) v.findViewById(R.id.team_30);
        x.setOnClickListener(this);
        CardView y = (CardView) v.findViewById(R.id.team_27);
        y.setOnClickListener(this);
        return v;
        // Inflate the layout for this fragment
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.team_1:
            case R.id.team_2:
            case R.id.team_3:
            case R.id.team_4:
            case R.id.team_5:
            case R.id.team_6:
            case R.id.team_7:
            case R.id.team_8:
            case R.id.team_9:
            case R.id.team_10:
            case R.id.team_11:
            case R.id.team_12:
            case R.id.team_13:
            case R.id.team_14:
            case R.id.team_15:
            case R.id.team_16:
            case R.id.team_17:
            case R.id.team_18:
            case R.id.team_19:
            case R.id.team_20:
            case R.id.team_21:
            case R.id.team_22:
            case R.id.team_23:
            case R.id.team_24:
            case R.id.team_25:
            case R.id.team_26:
            case R.id.team_27:
            case R.id.team_28:
            case R.id.team_29:
            case R.id.team_30:
                Intent intent = new Intent(getActivity(), NBA_Team_stat.class);
                CardView v2 = (CardView) v.findViewById(v.getId());
                String team_number = getResources().getResourceName(v2.getId());
                intent.putExtra("NAME_TEAM", team_number);
                startActivity(intent);
                break;
        }
    }
}
