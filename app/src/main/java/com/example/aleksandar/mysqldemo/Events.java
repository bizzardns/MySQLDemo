package com.example.aleksandar.mysqldemo;

import android.icu.util.Calendar;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ListView;

public class Events extends AppCompatActivity {
    CalendarView calendar;
    ListView lv = null;
    ListView ls;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        final RecyclerView rv = (RecyclerView) findViewById(R.id.rv2);
        rv.setLayoutManager(new LinearLayoutManager(Events.this));
        rv.setItemAnimator(new DefaultItemAnimator());

        calendar = (CalendarView) findViewById(R.id.calendarView);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                ls.setVisibility(View.VISIBLE);
                lv.setVisibility(View.VISIBLE);

                date = dayOfMonth + "." + (month + 1) + "." + year + ".";
            }
        });

    }
}
