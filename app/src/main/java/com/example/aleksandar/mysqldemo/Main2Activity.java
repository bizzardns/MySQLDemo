package com.example.aleksandar.mysqldemo;

import android.annotation.TargetApi;
import android.content.Intent;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aleksandar.mysqldemo.MySQL.SendReceive;
import com.example.aleksandar.mysqldemo.MySQL.SendReceiveSlobodni;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class Main2Activity extends AppCompatActivity {
    public static String sharedValue = null;
    String bend;
    String izabraniDatum;
    CalendarView calendar_unos;
    EditText ime, event, mesto, restoran;
    //Spinner spinner;
    //ArrayAdapter<String> adapter;
    BendList bendList = new BendList();
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigationView;
    String d;
    String m;
    String y;
    String date;
    Spinner cItems;
    Spinner bendovi;
    public static String jedan = null;
    public static String dva = null;
    public static String tri = null;
    public static String cetiri = null;
    public static String pet = null;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        setContentView(R.layout.activity_main2);

        /*Intent intent = getIntent();
        bend = intent.getStringExtra("key");*/

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mNavigationView = (NavigationView) findViewById(R.id.nav_item);
        mNavigationView.setItemIconTintList(null);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();
                if (id == R.id.nav1) {
                    Intent myIntent = new Intent(Main2Activity.this, SlobodniBendovi.class);
                    Main2Activity.this.startActivity(myIntent);

                } else if (id == R.id.nav2) {
                    Intent myIntent = new Intent(Main2Activity.this, Main2Activity.class);
                    Main2Activity.this.startActivity(myIntent);
                } else if (id == R.id.nav3) {
                    Intent myIntent = new Intent(Main2Activity.this, Counter.class);
                    Main2Activity.this.startActivity(myIntent);

                } else if (id == R.id.nav4) {
                    Intent myIntent = new Intent(Main2Activity.this, SmsActivity.class);
                    Main2Activity.this.startActivity(myIntent);

                } else if (id == R.id.nav5) {
                    Intent myIntent = new Intent(Main2Activity.this, OfflineMode.class);
                    Main2Activity.this.startActivity(myIntent);

                } else if (id == R.id.nav6) {
                    Intent myIntent = new Intent(Main2Activity.this, Register.class);
                    Main2Activity.this.startActivity(myIntent);

                }


                return true;
            }
        });


        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        ime = (EditText) findViewById(R.id.editIme);
        mesto = (EditText) findViewById(R.id.editMesto);
        restoran = (EditText) findViewById(R.id.editRestoran);

        //spinner = (Spinner) findViewById(R.id.spinner2);
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        bendList.getData();
        //adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bendList.data);
        //spinner.setAdapter(adapter);

        setTitle("Rezervisi");
        Calendar calander2 = Calendar.getInstance();
        int a = calander2.get(Calendar.DAY_OF_MONTH);
        int s = calander2.get(Calendar.MONTH) + 1;
        int n = calander2.get(Calendar.YEAR);
        d = String.valueOf(a);
        m = String.valueOf(s);
        y = String.valueOf(n);
        izabraniDatum = d + "." + m + "." + y + ".";


        bendovi = (Spinner) findViewById(R.id.spinnerBand);
        ArrayAdapter<String> adapterband = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bendList.data);

        adapterband.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        bendovi.setAdapter(adapterband);

        bendovi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                bend = bendovi.getSelectedItem().toString();
                //LOGIKA ZA DOBAVLJANJE IZABRANOG BENDA!
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        /*DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker2);
        Calendar today = Calendar.getInstance();
        datePicker.setFirstDayOfWeek(Calendar.MONDAY);
        datePicker.init(
                today.get(Calendar.YEAR),
                today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        izabraniDatum = dayOfMonth + "." + "" + (monthOfYear + 1) + "." + "" + year + ".";
                    }
                });*/


        calendar_unos = (CalendarView) findViewById(R.id.calendarView2);
        calendar_unos.setFirstDayOfWeek(Calendar.MONDAY);
        calendar_unos.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                izabraniDatum = dayOfMonth + "." + (month + 1) + "." + year + ".";

                d = String.valueOf(dayOfMonth);
                m = String.valueOf((month + 1));


                //OVDE SE BIRA DATUM POMOCU KOJEG SE DOBAVLJAJU IZ BAZE SLOBODNI BENDOVI!

            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


        ArrayList<String> theList = new ArrayList<>();
        theList.add("2017");
        theList.add("2018");
        theList.add("2019");
        theList.add("2020");
        theList.add("2021");
        theList.add("2022");
        theList.add("2023");
        theList.add("2024");


        cItems = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, R.layout.spinner_item, theList);

        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cItems.setAdapter(adapter1);

        cItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String adate = cItems.getSelectedItem().toString();

                izabraniDatum = d + "." + m + "." + adate + ".";

                //  Toast.makeText(getApplicationContext(),izabraniDatum, Toast.LENGTH_SHORT).show();

                try {
                    calendar_unos.setDate(new SimpleDateFormat("dd.MM.yyyy.").parse(izabraniDatum).getTime(), false, true);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {


            }
        });

    }



    public void rez(View view) {
        setTitle(izabraniDatum + " " + bend);
        String imeBenda = bend;
        String datum = izabraniDatum;
        String str_ime = ime.getText().toString();
        String str_mesto = mesto.getText().toString();
        String str_restoran = restoran.getText().toString();
        Main2Activity.jedan = imeBenda;
        Main2Activity.dva = datum;
        Main2Activity.tri = str_ime;
        Main2Activity.cetiri = str_mesto;
        Main2Activity.pet = str_restoran;
        String type = "rezervisi";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, imeBenda, datum, str_ime, str_mesto, str_restoran);




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




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tool, menu);
        return true;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mToggle.onOptionsItemSelected(item)) {

            return true;

        }
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.today) {
            //Toast.makeText(getApplicationContext(),date, Toast.LENGTH_SHORT).show();
            calendar_unos.setDate(Calendar.getInstance().getTimeInMillis(), false, true);
            Calendar calander = Calendar.getInstance();
            int a = calander.get(Calendar.DAY_OF_MONTH);
            int s = calander.get(Calendar.MONTH) + 1;
            int n = calander.get(Calendar.YEAR);
            d = String.valueOf(a);
            m = String.valueOf(s);
            y = String.valueOf(n);
            izabraniDatum = d + "." + m + "." + y + ".";

            // Toast.makeText(getApplicationContext(),kurcina, Toast.LENGTH_SHORT).show();


            return true;
        }
        if (id == R.id.spinner) {
            cItems.performClick();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
