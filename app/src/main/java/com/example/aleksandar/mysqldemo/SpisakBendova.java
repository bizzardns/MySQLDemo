package com.example.aleksandar.mysqldemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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


            CustomList adapter = new
                    CustomList(SpisakBendova.this,datum.data , list.data);
           ListView list=(ListView)findViewById(R.id.list);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    String val =(String) parent.getItemAtPosition(position);


                    //ovde da ubacim logiku

                    //Toast.makeText(getApplicationContext(), val,
                            //Toast.LENGTH_SHORT).show();



                }
            });

        }









    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Rezervisi").setIntent(new Intent(this, Main2Activity.class));
        menu.add("Obrisi rezervaciju").setIntent(new Intent(this, Main3Activity.class));
        menu.add("Broj svadbi").setIntent(new Intent(this, Counter.class));
        menu.add("Admin panel").setIntent(new Intent(this, Register.class));
        menu.add("Posalji obavestenje").setIntent(new Intent(this, SmsActivity.class));


        return true;

    }



}
