package com.example.android_project.Fragment;

import android.app.AlertDialog;
import android.app.Application;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.example.android_project.asynctasks.AsyncGetSpecific;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class GamesFragment extends Fragment {

    private Games_Adapter game_adapter;
    AsyncGetSpecific asyncTask;

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
        // Creation de la liste et lien avec l'adapter
        // Inflate the layout for this fragment
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
            Log.i("ASYNC, ", date);
            date = date.split(":")[1];
            Log.i("ASYNC", "Date is : " + date);
            game_adapter.clear();
            game_adapter.notifyDataSetChanged();
            asyncTask = new AsyncGetSpecific(game_adapter);
            asyncTask.execute("games", date);
        });

        Log.i("FRAG", "Je suis dans le GamesFragment");
        if (game_adapter == null) {
            game_adapter = new Games_Adapter(this.getActivity());
        }
        list.setAdapter(game_adapter);
        asyncTask = new AsyncGetSpecific(game_adapter);
        Log.i("SAVE", "Launch ASYNC");
        asyncTask.execute("games", "2019-01-30");
        // Inflate the layout for this fragment
        //CardView main_card = (CardView) view.findViewById(R.id.card_main);
        return view;
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            /*
                We should use THEME_HOLO_LIGHT, THEME_HOLO_DARK or THEME_TRADITIONAL only.

                The THEME_DEVICE_DEFAULT_LIGHT and THEME_DEVICE_DEFAULT_DARK does not work
                perfectly. This two theme set disable color of disabled dates but users can
                select the disabled dates also.

                Other three themes act perfectly after defined enabled date range of date picker.
                Those theme completely hide the disable dates from date picker object.
             */
            DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                    AlertDialog.THEME_HOLO_LIGHT, this, year, month, day);

            /*
                add(int field, int value)
                    Adds the given amount to a Calendar field.
             */
            // Add 3 days to Calendar
            calendar.add(Calendar.DATE, 3);

            /*
                getTimeInMillis()
                    Returns the time represented by this Calendar,
                    recomputing the time from its fields if necessary.

                getDatePicker()
                Gets the DatePicker contained in this dialog.

                setMinDate(long minDate)
                    Sets the minimal date supported by this NumberPicker
                    in milliseconds since January 1, 1970 00:00:00 in getDefault() time zone.

                setMaxDate(long maxDate)
                    Sets the maximal date supported by this DatePicker in milliseconds
                    since January 1, 1970 00:00:00 in getDefault() time zone.
             */

            // Set the Calendar new date as maximum date of date picker
            Calendar c = Calendar.getInstance();
            c.set(2021, 1, 8);//Year,Mounth -1,Day
            dpd.getDatePicker().setMaxDate(c.getTimeInMillis());
            // Set the Calendar Date as minimum date of date picker
            c.set(2019, 0, 30);//Year,Mounth -1,Day
            dpd.getDatePicker().setMinDate(c.getTimeInMillis());

            // So, now date picker selectable date range is from Jan 2019 to Feb 2021 days only

            // Return the DatePickerDialog
            return dpd;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the chosen date
            TextView tv = (TextView) getActivity().findViewById(R.id.tv);

            // Create a Date variable/object with user chosen date
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(0);
            cal.set(year, month, day, 0, 0, 0);
            String strmonth = String.valueOf(month);
            String strday = String.valueOf(day);
            if (month < 10) strmonth = "0" + String.valueOf(month);
            if (day < 10) {
                strday = "0" + String.valueOf(day);
            } else if (day > 10 & day < 20) {
                strday = "1" + String.valueOf(day);
            } else if (day > 20 & day < 30) {
                strday = "2" + String.valueOf(day);
            } else strday = "3" + String.valueOf(day);
            Log.i("DATE", year + "-" + strmonth + "-" + strday);
            Date chosenDate = cal.getTime();

            // Format the date using style and locale
            DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.FRANCE);
            String formattedDate = df.format(chosenDate);
            formattedDate = "List of all the games of :" + year + "-" + strmonth + "-" + strday;

            // Display the chosen date to app interface
            tv.setText(formattedDate);
        }
    }
}