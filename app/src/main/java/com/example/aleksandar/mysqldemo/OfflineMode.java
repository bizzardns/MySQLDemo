package com.example.aleksandar.mysqldemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aleksandar.mysqldemo.MySQL.SendRecieveEvent;
import com.example.aleksandar.mysqldemo.SQLiteData.GetEvent;
import com.example.aleksandar.mysqldemo.SQLiteData.GetGrad;
import com.example.aleksandar.mysqldemo.SQLiteData.GetIme;
import com.example.aleksandar.mysqldemo.SQLiteData.GetRestoran;

import java.util.ArrayList;

public class OfflineMode extends AppCompatActivity {


    ContactDB contactBase;
    android.widget.SearchView sv;
    ArrayAdapter<String> adapter;
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

        sv = (android.widget.SearchView) findViewById(R.id.search);
        final ListView listView = (ListView) findViewById(R.id.offline_list);


        final ArrayList<String> theList = new ArrayList<>();
        final Cursor cursor = contactBase.list_all_list();


        while (cursor.moveToNext()) {

            theList.add(cursor.getString(1)+":"+"\n "+cursor.getString(2)+""+"\n "+cursor.getString(3)+""+"\n "+cursor.getString(4)+""+"\n "+cursor.getString(6)+""+"\n "+cursor.getString(5));

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,theList);
            listView.setAdapter(adapter);


        }

        sv.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }


            @Override
            public boolean onQueryTextChange(String text) {

                adapter.getFilter().filter(text);

                return false;
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {


            }
        });


    }
}