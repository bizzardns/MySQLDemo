package com.example.aleksandar.mysqldemo;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;


public class Main3Activity extends AppCompatActivity {

    String bend;
    String izabraniDatum;
    CalendarView calendar;
    //Spinner spinner;
    //ArrayAdapter<String> adapter;
    BendList bendList = new BendList();
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigationView;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
        setContentView(R.layout.activity_main3);



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
                    Intent myIntent = new Intent(Main3Activity.this, Main2Activity.class);
                    Main3Activity.this.startActivity(myIntent);

                }
                else if (id == R.id.nav2)
                {
                    Intent myIntent = new Intent(Main3Activity.this, Main3Activity.class);
                    Main3Activity.this.startActivity(myIntent);
                }
                else if (id == R.id.nav3)
                {
                    Intent myIntent = new Intent(Main3Activity.this, SlobodniBendovi.class);
                    Main3Activity.this.startActivity(myIntent);

                }
                else if (id == R.id.nav4)
                {
                    Intent myIntent = new Intent(Main3Activity.this, Counter.class);
                    Main3Activity.this.startActivity(myIntent);

                }
                else if (id == R.id.nav5)
                {
                    Intent myIntent = new Intent(Main3Activity.this, SmsActivity.class);
                    Main3Activity.this.startActivity(myIntent);

                }
                else if (id == R.id.nav6)
                {
                    Intent myIntent = new Intent(Main3Activity.this, Register.class);
                    Main3Activity.this.startActivity(myIntent);

                }


                return true;
            }
        } );



        //spinner = (Spinner) findViewById(R.id.spinner2);
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        bendList.getData();
        //adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bendList.data);
        //spinner.setAdapter(adapter);


        setTitle("Obrisi");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bendList.data);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner sItems = (Spinner) findViewById(R.id.spinnerBand);
        sItems.setAdapter(adapter);


        sItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                bend = sItems.getSelectedItem().toString();

                //LOGIKA ZA DOBAVLJANJE IZABRANOG BENDA!
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        calendar = (CalendarView) findViewById(R.id.calendarView3);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                izabraniDatum = dayOfMonth + "." + (month + 1) + "." + year + ".";

                //OVDE SE BIRA DATUM POMOCU KOJEG SE DOBAVLJAJU IZ BAZE SLOBODNI BENDOVI!

            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main2 Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


    public boolean onCreateOptionsMenu(Menu menu) {


        menu.add("Rezervisi").setIntent(new Intent(this, Main2Activity.class));
        menu.add("Pregled rezervacija").setIntent(new Intent(this, SpisakBendova.class));
        menu.add("Broj svadbi").setIntent(new Intent(this, Counter.class));
        menu.add("Admin panel").setIntent(new Intent(this, Register.class));
        menu.add("Posalji obavestenje").setIntent(new Intent(this, SmsActivity.class));
        menu.add("Kalendar").setIntent(new Intent(this, SlobodniBendovi.class));



        return true;

    }

    public void brisi(View v) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        setTitle(izabraniDatum + " " + bend); //LOGIKA ZA BRISANJE IZ BAZE


                        String imeBenda = bend;
                        String datum = izabraniDatum;
                        String type = "obrisi";
                        BackgroundWorker backgroundWorker = new BackgroundWorker(Main3Activity.this);
                        backgroundWorker.execute(type, imeBenda, datum);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        // Toast.makeText(Main3Activity.this, "NO", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){

            return true;

        }

        return super.onOptionsItemSelected(item);
    }

}
