package com.example.android_project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

import com.example.android_project.Fragment.GamesFragment;
import com.example.android_project.Fragment.HomeFragment;
import com.example.android_project.Fragment.StatsFragment;
import com.example.android_project.Fragment.TeamFragment;
import com.example.android_project.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayDeque;
import java.util.Deque;

public class NBA_Home extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Deque<Integer> intDq= new ArrayDeque<>(4);
    boolean flag= true;

    private Fragment MyGameFragment = null;
    private Fragment MyTeamFragment = null;
    private Fragment MyStatsFragment = null;
    private Fragment MyHomeFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_b_a__home);
        getSupportActionBar().setTitle("Home");

        bottomNavigationView = findViewById(R.id.bottom_nav_id);

        //add home item in the list
        intDq.push(R.id.home_id);
        loadFragment(new HomeFragment());

        bottomNavigationView.setSelectedItemId(R.id.home_id);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id= item.getItemId();
            if(intDq.contains(id)){
                if(id== R.id.home_id){
                    if (intDq.size()!=1){
                        if (flag){
                            intDq.addFirst(R.id.home_id);
                            flag=false;
                        }
                    }
                }
                intDq.remove(id);
            }
            intDq.push(id);
            loadFragment(getFragment(item.getItemId()));
            return true;
        });
    }

    private Fragment getFragment(int itemId) {
        switch (itemId){
            case R.id.home_id:
                bottomNavigationView.getMenu().getItem(0).setChecked(true);
                if(MyHomeFragment == null)MyHomeFragment = new HomeFragment();
                return MyHomeFragment;
                case R.id.game_id:
                bottomNavigationView.getMenu().getItem(1).setChecked(true);
                if(MyGameFragment == null) MyGameFragment = new GamesFragment();
                return MyGameFragment;
            case R.id.statistic_id:
                bottomNavigationView.getMenu().getItem(2).setChecked(true);
                if(MyStatsFragment == null) MyStatsFragment = new StatsFragment();
                return MyStatsFragment;
            case R.id.team_id:
                bottomNavigationView.getMenu().getItem(3).setChecked(true);
                if(MyTeamFragment == null)MyTeamFragment = new TeamFragment();
                return MyTeamFragment;
        }
        bottomNavigationView.getMenu().getItem(0).setChecked(true);
        return new HomeFragment();
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,fragment,fragment.getClass().getSimpleName()).commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("SAVE", "Je suis dans le save instance NBA_Home");
    }


    @Override
    public void onBackPressed() {
        intDq.pop();
        if (!intDq.isEmpty()){
            loadFragment(getFragment(intDq.peek()));
        }else{
            finish();
        }
    }
}

