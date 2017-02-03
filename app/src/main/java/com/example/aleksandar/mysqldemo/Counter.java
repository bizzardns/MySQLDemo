package com.example.aleksandar.mysqldemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        brojacDatuma.getDatum();

        CustomList adapter = new
                CustomList(Counter.this,brojacDatuma.data, brojacBendova.data);
        ListView list=(ListView)findViewById(R.id.lv);
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
}
