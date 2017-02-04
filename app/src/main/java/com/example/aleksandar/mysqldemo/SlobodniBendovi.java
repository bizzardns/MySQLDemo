package com.example.aleksandar.mysqldemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SlobodniBendovi extends AppCompatActivity {

    CalendarView calendar;
    Test list = new Test();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slobodni_bendovi);
        list.getData();


        calendar = (CalendarView) findViewById(R.id.calendarView2);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth ) {

                String date = dayOfMonth +"." + (month + 1) +"."+ year;



                    //OVDE SE BIRA DATUM POMOCU KOJEG SE DOBAVLJAJU IZ BAZE SLOBODNI BENDOVI!

                Toast.makeText(getApplicationContext(),date,Toast.LENGTH_SHORT).show();

            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list.data);

        listView = (ListView) findViewById(R.id.lista);
        listView.setAdapter(adapter);









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
