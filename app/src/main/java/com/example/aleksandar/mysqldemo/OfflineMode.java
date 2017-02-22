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

        contactBase = new ContactDB(this, null, 1);

        sv = (android.widget.SearchView) findViewById(R.id.search);
        final ListView listView = (ListView) findViewById(R.id.offline_list);


        final ArrayList<String> theList = new ArrayList<>();
        final Cursor cursor = contactBase.list_all_list();

        while (cursor.moveToNext()) {

            theList.add(cursor.getString(1)+" "+cursor.getString(2));

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
                String val = (String) parent.getItemAtPosition(position);
                cursor.moveToPosition(position);
               /*event = cursor.getString(3);
                 String bend = cursor.getString(1);
                   event = cursor.getString(3);
                ime = cursor.getString(4);
                restoran = cursor.getString(6);
                grad = cursor.getString(5);


                AlertDialog.Builder builder1 = new AlertDialog.Builder(OfflineMode.this);


                builder1.setPositiveButton("Nazad",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                            }
                        });
                builder1.setIcon(R.drawable.dijamant);

                TextView title = new TextView(getApplicationContext());

                title.setText(val);
                title.setBackgroundColor(Color.DKGRAY);
                title.setPadding(10, 10, 10, 10);
                title.setGravity(Gravity.CENTER);
                title.setTextColor(Color.WHITE);
                title.setTextSize(20);

                builder1.setCustomTitle(title);

                builder1.setMessage("Event: " + event +"\n" +"Ime: " + ime +"\n" + "Restoran: " + restoran +"\n"+ "Grad: " + grad +"\n" );

                AlertDialog dialog = builder1.create();



                builder1.show();*/









                //Toast.makeText(OfflineMode.this,broj ,Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" +broj )));


            }
        });


    }
}
