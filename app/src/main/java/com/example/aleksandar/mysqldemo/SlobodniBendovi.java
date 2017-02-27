package com.example.aleksandar.mysqldemo;

import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;

import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.aleksandar.mysqldemo.MySQL.SendReceive;
import com.example.aleksandar.mysqldemo.MySQL.SendRecieveEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class SlobodniBendovi extends AppCompatActivity {


    CalendarView calendar;
    ImageView img;
    ListView lv = null;
    ListView ls;
    String d;
    String m;
    String y;
    String date;
    String selectedDate;
    String bend;
    String izabraniDatum;
    String long_click;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigationView;
    String urlAdress = "http://macakmisamuzika.com/android/freebands.php";
    String urlAdress_reserved = "http://macakmisamuzika.com/android/reservedOnDate.php";
    String urlEventData = "http://macakmisamuzika.com/android/eventData.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        setContentView(R.layout.activity_slobodni_bendovi);

        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mNavigationView = (NavigationView) findViewById(R.id.nav_item);
        mNavigationView.setItemIconTintList(null);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.nav1) {
                    Intent myIntent = new Intent(SlobodniBendovi.this, Main2Activity.class);
                    SlobodniBendovi.this.startActivity(myIntent);

                }
                else if (id == R.id.nav2)
                {
                    Intent myIntent = new Intent(SlobodniBendovi.this, Main3Activity.class);
                    SlobodniBendovi.this.startActivity(myIntent);
                }
                else if (id == R.id.nav3)
                {
                    Intent myIntent = new Intent(SlobodniBendovi.this, SlobodniBendovi.class);
                    SlobodniBendovi.this.startActivity(myIntent);

                }
                else if (id == R.id.nav4)
                {
                    Intent myIntent = new Intent(SlobodniBendovi.this, Counter.class);
                    SlobodniBendovi.this.startActivity(myIntent);

                }
                else if (id == R.id.nav5)
                {
                    Intent myIntent = new Intent(SlobodniBendovi.this, SmsActivity.class);
                    SlobodniBendovi.this.startActivity(myIntent);

                }
                else if (id == R.id.nav6)
                {
                    Intent myIntent = new Intent(SlobodniBendovi.this, Register.class);
                    SlobodniBendovi.this.startActivity(myIntent);

                }


                return true;
            }
        } );


        setTitle("Kalendar");


        ls = (ListView) findViewById(R.id.ls);
        img = (ImageView) findViewById(R.id.imageView);
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


                date = dayOfMonth + "." + (month + 1) + "." + year + ".";
                  d = String.valueOf(dayOfMonth);
                  m= String.valueOf((month + 1));
                SendReceive pr = new SendReceive(urlAdress, SlobodniBendovi.this, date, ls);
                pr.execute();

                SendReceive sr = new SendReceive(urlAdress_reserved, SlobodniBendovi.this, date, lv);
                sr.execute();



                //OVDE SE BIRA DATUM POMOCU KOJEG SE DOBAVLJAJU IZ BAZE SLOBODNI BENDOVI!

            }
        });

        ArrayList<String> theList = new ArrayList<>();
        theList.add("2017");
        theList.add("2018");
        theList.add("2019");
        theList.add("2020");
        theList.add("2021");
        theList.add("2022");
        theList.add("2023");
        theList.add("2024");
        theList.add("2025");
        theList.add("2026");
        theList.add("2027");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, theList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner sItems = (Spinner) findViewById(R.id.spinner2);
        sItems.setAdapter(adapter);

        sItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                y = sItems.getSelectedItem().toString();



                selectedDate = d+"/"+ m +"/"+ y;
                try {
                    calendar.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(selectedDate).getTime(), true, true);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {



            }
        });




        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                 long_click = (String) parent.getItemAtPosition(position);
                brisi();
               // Toast.makeText(getApplicationContext(), long_click +" "+ date, Toast.LENGTH_SHORT).show();

                return false;
            }


        });

        ls.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                long_click = (String) parent.getItemAtPosition(position);

                Intent myIntent = new Intent(SlobodniBendovi.this, UpisIzKalendara.class);
                myIntent.putExtra("Ime", long_click);
                myIntent.putExtra("Datum", date);
                SlobodniBendovi.this.startActivity(myIntent);

               // Toast.makeText(getApplicationContext(), long_click, Toast.LENGTH_SHORT).show();

                return false;
            }


        });




       /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {



           AlertDialog.Builder builder1 = new AlertDialog.Builder(SlobodniBendovi.this);

                String val = (String) parent.getItemAtPosition(position);
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



                View modelView = getLayoutInflater().inflate(R.layout.activity_events, null);
                RecyclerView rv2 = (RecyclerView) modelView.findViewById(R.id.rv2);
                rv2.setLayoutManager(new LinearLayoutManager(SlobodniBendovi.this));

                SendRecieveEvent sre = new SendRecieveEvent(SlobodniBendovi.this, urlEventData, rv2, val, date);
                sre.execute();

                builder1.setView(modelView);
                AlertDialog dialog = builder1.create();



                dialog.show();



            }
        });*/
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Rezervisi").setIntent(new Intent(this, Main2Activity.class));
        menu.add("Obrisi rezervaciju").setIntent(new Intent(this, Main3Activity.class));
        menu.add("Broj svadbi").setIntent(new Intent(this, Counter.class));
        menu.add("Admin panel").setIntent(new Intent(this, Register.class));
        menu.add("Posalji obavestenje").setIntent(new Intent(this, SmsActivity.class));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){

            return true;

        }

        return super.onOptionsItemSelected(item);
    }
    public void brisi() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        //setTitle(izabraniDatum + " " + bend); //LOGIKA ZA BRISANJE IZ BAZE


                        String imeBenda = long_click;
                        String datum = date;
                        String type = "obrisi";
                        BackgroundWorker backgroundWorker = new BackgroundWorker(SlobodniBendovi.this);
                        backgroundWorker.execute(type, imeBenda, datum);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        // Toast.makeText(Main3Activity.this, "NO", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setMessage("Da li želite da obrišete rezervaciju?").setPositiveButton("Da", dialogClickListener)
                .setNegativeButton("Ne", dialogClickListener).show();


    }

}
