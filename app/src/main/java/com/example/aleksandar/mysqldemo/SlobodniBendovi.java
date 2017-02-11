package com.example.aleksandar.mysqldemo;

import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.ListView;


import com.example.aleksandar.mysqldemo.MySQL.SendReceive;


public class SlobodniBendovi extends AppCompatActivity {


    CalendarView calendar;
    ImageView img;
    ListView lv = null;
    ListView ls;

    String urlAdress = "http://lp-developers.com/freebands.php";
    String urlAdress_reserved = "http://lp-developers.com/reservedOnDate.php";
    String urlEventData = "http://lp-developers.com/eventData.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
        setContentView(R.layout.activity_slobodni_bendovi);
        setTitle("Kalendar");


        ls = (ListView) findViewById(R.id.ls);
        img =(ImageView) findViewById(R.id.imageView);
        img.setVisibility(View.VISIBLE);

        lv = (ListView) findViewById(R.id.lv);
        lv.setVisibility(View.INVISIBLE);
        ls.setVisibility(View.INVISIBLE);







        calendar = (CalendarView) findViewById(R.id.calendarView);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                img.setVisibility(View.GONE);
                ls.setVisibility(View.VISIBLE);
                lv.setVisibility(View.VISIBLE);

                String date = dayOfMonth + "." + (month + 1) + "." + year + ".";
                SendReceive pr = new SendReceive(urlAdress, SlobodniBendovi.this, date, ls);
                pr.execute();
                SendReceive sr = new SendReceive(urlAdress_reserved, SlobodniBendovi.this, date, lv);
                sr.execute();

                //OVDE SE BIRA DATUM POMOCU KOJEG SE DOBAVLJAJU IZ BAZE SLOBODNI BENDOVI!

            }
        });

       lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String val =(String) parent.getItemAtPosition(position);


                AlertDialog.Builder builder1 = new AlertDialog.Builder(SlobodniBendovi.this);
                builder1.setIcon(R.drawable.dijamant);
                builder1.setTitle(val);
                builder1.setMessage("Event: "+"\n"+"\n"+"Ime: "+"\n"+"\n"+"Grad: "+"\n"+"\n"+ "Restoran: " );
                builder1.setIcon(R.drawable.dijamant);
                builder1.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        });
                builder1.show();



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
