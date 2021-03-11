package com.example.android_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayDeque;
import java.util.Deque;

public class NBA_Home extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Deque<Integer> intDq= new ArrayDeque<>(4);
    boolean flag= true;

    private Fragment MyFragment = null;

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

            if (savedInstanceState != null) {
                //Restore the fragment's instance
                MyFragment = getSupportFragmentManager().getFragment(savedInstanceState, "myFragmentName");
                Log.i("SAVE", "Je restaure l'instance du fragment");
                loadFragment(MyFragment);
            }else{
                loadFragment(getFragment(item.getItemId()));
            }
            return true;
        });
    }

    private Fragment getFragment(int itemId) {
        switch (itemId){
            case R.id.home_id:
                bottomNavigationView.getMenu().getItem(0).setChecked(true);
                return new HomeFragment();
            case R.id.game_id:
                bottomNavigationView.getMenu().getItem(1).setChecked(true);
                return new GamesFragment();
            case R.id.statistic_id:
                bottomNavigationView.getMenu().getItem(2).setChecked(true);
                return new StatsFragment();
            case R.id.team_id:
                bottomNavigationView.getMenu().getItem(3).setChecked(true);
                return new TeamFragment();

        }
        bottomNavigationView.getMenu().getItem(0).setChecked(true);
        return new HomeFragment();
    }

    private void loadFragment(Fragment fragment) {
        MyFragment = fragment;
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,fragment,fragment.getClass().getSimpleName()).commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("SAVE", "Je suis dans le save instance");
        //Save the fragment's instance
        getSupportFragmentManager().putFragment(outState, "myFragmentName", MyFragment);
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

