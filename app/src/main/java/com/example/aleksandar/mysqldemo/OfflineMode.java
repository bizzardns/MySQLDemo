package com.example.aleksandar.mysqldemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aleksandar.mysqldemo.MySQL.SendReceive;
import com.example.aleksandar.mysqldemo.MySQL.SendRecieveEvent;
import com.example.aleksandar.mysqldemo.SQLiteData.GetEvent;
import com.example.aleksandar.mysqldemo.SQLiteData.GetGrad;
import com.example.aleksandar.mysqldemo.SQLiteData.GetIme;
import com.example.aleksandar.mysqldemo.SQLiteData.GetRestoran;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class OfflineMode extends AppCompatActivity {


    ContactDB contactBase;
    android.widget.SearchView sv;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter1;
    CalendarView calendar;
    String date;
    ListView listView;
    ListView listView2;
    String event;
    String ime;
    String grad;
    String restoran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_mode);
         setTitle("Offline mode");
        contactBase = new ContactDB(this, null, 1);



        listView = (ListView) findViewById(R.id.offline_list2);
        listView2 = (ListView) findViewById(R.id.offline_list);
        calendar = (CalendarView) findViewById(R.id.calendarView);

        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                ArrayList<String> theList = new ArrayList<>();
                ArrayList<String> theList2 = new ArrayList<>();
                ArrayList<String> theList3 = new ArrayList<>();
                Cursor cursor = contactBase.list_all_list();

                date = dayOfMonth + "." + (month + 1) + "." + year + ".";

                adapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,theList);
                listView.setAdapter(adapter1);

                while (cursor.moveToNext()) {

                    if(cursor.getString(2).contains(date)){

                        theList.add(cursor.getString(1)+"\n "+cursor.getString(6)+""+"\n "+cursor.getString(5));
                        theList3.add(cursor.getString(1));

                        Collections.sort(theList, new Comparator<String>() {
                            @Override
                            public int compare(String s1, String s2) {
                                return s1.compareToIgnoreCase(s2);
                            }
                        });
                        adapter1 = new ArrayAdapter<String>(OfflineMode.this, android.R.layout.simple_list_item_1,theList);
                        listView.setAdapter(adapter1);

                    }else if (!cursor.getString(2).contains(date)){


                        theList2.add(cursor.getString(1));
                        HashSet<String> hashSet = new HashSet<String>();
                        hashSet.addAll(theList2);
                        theList2.clear();
                        theList2.removeAll(theList);
                        theList2.addAll(hashSet);
                        theList2.removeAll(theList3);
                        Collections.sort(theList2, new Comparator<String>() {
                            @Override
                            public int compare(String s1, String s2) {
                                return s1.compareToIgnoreCase(s2);
                            }
                        });
                        adapter = new ArrayAdapter<String>(OfflineMode.this,android.R.layout.simple_list_item_1,theList2);
                        listView2.setAdapter(adapter);

                    }

                }










            }
        });



















    }


}