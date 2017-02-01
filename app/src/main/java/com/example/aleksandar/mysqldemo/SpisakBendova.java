package com.example.aleksandar.mysqldemo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SpisakBendova extends AppCompatActivity {

    ReservationList list = new ReservationList();
    DatumList datum = new DatumList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spisak_bendova);
        setTitle("Lista svadbi");

        list.getData();
      datum.getData();




        final ListView listView = (ListView) findViewById(R.id.list);
        final ArrayList<String> theList = new ArrayList<>();

            final ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datum.data);
            listView.setAdapter(listAdapter);





        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                String val =(String) parent.getItemAtPosition(position);
                //ovde da ubacim logiku

                Toast.makeText(getApplicationContext(), val,
                        Toast.LENGTH_SHORT).show();



            }
        });





    }
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Rezervisi").setIntent(new Intent(this, Main2Activity.class));
        menu.add("Obrisi rezervaciju").setIntent(new Intent(this, Main3Activity.class));
        menu.add("Broj svadbi").setIntent(new Intent(this, SpisakBendova.class));
        menu.add("Dodaj admina").setIntent(new Intent(this, Register.class));
        menu.add("Posalji obavestenje").setIntent(new Intent(this, SmsActivity.class));


        return true;

    }
}
