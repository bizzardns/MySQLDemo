package com.example.aleksandar.mysqldemo;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.aleksandar.mysqldemo.Recycler.Adapter;

import java.util.ArrayList;


public class TestiranjeOffline extends AppCompatActivity {

    ContactDB contactBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testiranje_offline);

        ListView list = (ListView) findViewById(R.id.tester);
        contactBase = new ContactDB(this, null, 1);
        final ArrayList<String> theList = new ArrayList<>();
         Cursor cursor = contactBase.list_all_list();

        while (cursor.moveToNext()) {

            theList.add(cursor.getString(2) +" "+cursor.getString(1));

            ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
            list.setAdapter(listAdapter);

        }

    }
}
