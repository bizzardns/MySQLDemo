package com.example.aleksandar.mysqldemo;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.CalendarView;
import android.widget.ListView;

import com.example.aleksandar.mysqldemo.MySQL.SendReceive;

public class SlobodniBendovi extends AppCompatActivity {

    CalendarView calendar;

    ListView lv;
    String urlAdress = "http://lp-developers.com/freebands.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slobodni_bendovi);
        setTitle("Slobodni bendovi");
        lv = (ListView) findViewById(R.id.lv);

        calendar = (CalendarView) findViewById(R.id.calendarView);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                String date = dayOfMonth + "." + (month + 1) + "." + year + ".";

                SendReceive sr = new SendReceive(urlAdress, SlobodniBendovi.this, date, lv);
                sr.execute();
                //OVDE SE BIRA DATUM POMOCU KOJEG SE DOBAVLJAJU IZ BAZE SLOBODNI BENDOVI!

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
