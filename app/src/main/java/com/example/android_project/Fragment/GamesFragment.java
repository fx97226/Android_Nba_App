package com.example.android_project.Fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android_project.R;
import com.example.android_project.adaptor.Games_Adapter;
import com.example.android_project.asynctasks.AsyncGetAll;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class GamesFragment extends Fragment {

    private Games_Adapter game_adapter;
    AsyncGetAll asyncTask;

    public GamesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Games");
        View view = inflater.inflate(R.layout.fragment_games, container, false);
        ListView list = (ListView) view.findViewById(R.id.list);
        Button btn = (Button) view.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initialize a new date picker dialog fragment
                DialogFragment dFragment = new DatePickerFragment();
                // Show the date picker dialog fragment
                dFragment.show(getFragmentManager(), "Date Picker");
            }
        });

        Button update = (Button) view.findViewById(R.id.update);
        update.setOnClickListener(v -> {
            TextView tv = (TextView) getActivity().findViewById(R.id.tv);
            String date = (String) tv.getText().toString();
            if (date.length() == 0) {
                date = " :2019-01-30";
            }
            date = date.split(":")[1];
            game_adapter.clear();
            game_adapter.notifyDataSetChanged();
            asyncTask = new AsyncGetAll(game_adapter);
            asyncTask.execute("games", date);
        });

        Log.i("FRAG", "Je suis dans le GamesFragment");
        if (game_adapter == null) {
            game_adapter = new Games_Adapter(this.getActivity());
            list.setAdapter(game_adapter);
            asyncTask = new AsyncGetAll(game_adapter);
            asyncTask.execute("games", "2021-02-08");
        }
        list.setAdapter(game_adapter);
        return view;
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                    AlertDialog.THEME_HOLO_LIGHT, this, year, month, day);
            calendar.add(Calendar.DATE, 3);

            // Set the Calendar new date as maximum date of date picker
            Calendar c = Calendar.getInstance();
            //The user cannot go further than the dates from API
            // Could be automatic but no request allow us to get the range of the games.
            c.set(2021, 1, 8);//Year,Mounth -1,Day
            dpd.getDatePicker().setMaxDate(c.getTimeInMillis());
            // Set the Calendar Date as minimum date of date picker
            c.set(2019, 0, 30);
            dpd.getDatePicker().setMinDate(c.getTimeInMillis());

            // Return the DatePickerDialog
            return dpd;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the chosen date
            TextView tv = (TextView) getActivity().findViewById(R.id.tv);

            // Create a valid YYYY-MM-DD date with user chosen date
            String strmonth = String.valueOf(month);
            String strday = String.valueOf(day);
            if (month < 10) strmonth = "0" + month;
            if (day < 10) {
                strday = "0" + day;
            }
            Log.i("DATE", year + "-" + strmonth + "-" + strday);
            String formattedDate = "List of all the games of :" + year + "-" + strmonth + "-" + strday;
            // Display the chosen date to app interface
            tv.setText(formattedDate);
        }
    }
}