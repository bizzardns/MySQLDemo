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
import android.widget.Toast;

import java.util.ArrayList;

public class SpisakBendova extends AppCompatActivity {

    BendList bendList = new BendList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spisak_bendova);

        bendList.getData();


        final ListView listView = (ListView) findViewById(R.id.list);
        final ArrayList<String> theList = new ArrayList<>();




            //theList.add(cursor.getString(2));

            ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bendList.data);
            listView.setAdapter(listAdapter);





        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                //cursor.moveToPosition(position);
               // String broj = cursor.getString(1);

                Toast.makeText(SpisakBendova.this,"Radi!!!" ,Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" +broj )));


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
