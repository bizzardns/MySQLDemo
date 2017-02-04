package com.example.aleksandar.mysqldemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class Counter extends AppCompatActivity {

    BrojacBendova brojacBendova = new BrojacBendova();
    BrojacDatuma brojacDatuma = new BrojacDatuma();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        setTitle("Broj svadbi po bendu");

        brojacBendova.getData();
        brojacDatuma.getData();


        CustomList ada = new
                CustomList(Counter.this, brojacBendova.data,brojacDatuma.data);
        ListView list=(ListView)findViewById(R.id.lv);
        list.setAdapter(ada);
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
        menu.add("Pregled rezervacija").setIntent(new Intent(this, SpisakBendova.class));
        menu.add("Admin panel").setIntent(new Intent(this, Register.class));
        menu.add("Posalji obavestenje").setIntent(new Intent(this, SmsActivity.class));
        menu.add("Slobodni bendovi").setIntent(new Intent(this, SlobodniBendovi.class));


        return true;

    }
}
