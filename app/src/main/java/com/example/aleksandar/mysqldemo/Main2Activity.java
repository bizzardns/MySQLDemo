package com.example.aleksandar.mysqldemo;

import android.annotation.TargetApi;
import android.content.Intent;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;




public class Main2Activity extends AppCompatActivity {

    String bend;
    String izabraniDatum;
    CalendarView calendar;
    EditText ime, event, mesto, restoran;
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
        setContentView(R.layout.activity_main2);

        /*Intent intent = getIntent();
        bend = intent.getStringExtra("key");*/

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
                    Intent myIntent = new Intent(Main2Activity.this, Main2Activity.class);
                    Main2Activity.this.startActivity(myIntent);

                }
                else if (id == R.id.nav2)
                {
                    Intent myIntent = new Intent(Main2Activity.this, Main3Activity.class);
                    Main2Activity.this.startActivity(myIntent);
                }
                else if (id == R.id.nav3)
                {
                    Intent myIntent = new Intent(Main2Activity.this, SlobodniBendovi.class);
                    Main2Activity.this.startActivity(myIntent);

                }
                else if (id == R.id.nav4)
                {
                    Intent myIntent = new Intent(Main2Activity.this, Counter.class);
                    Main2Activity.this.startActivity(myIntent);

                }
                else if (id == R.id.nav5)
                {
                    Intent myIntent = new Intent(Main2Activity.this, SmsActivity.class);
                    Main2Activity.this.startActivity(myIntent);

                }
                else if (id == R.id.nav6)
                {
                    Intent myIntent = new Intent(Main2Activity.this, Register.class);
                    Main2Activity.this.startActivity(myIntent);

                }


                return true;
            }
        } );






        event = (EditText)findViewById(R.id.editEvent);
        ime = (EditText)findViewById(R.id.editIme);
       mesto = (EditText)findViewById(R.id.editMesto);
       restoran = (EditText)findViewById(R.id.editRestoran);

        //spinner = (Spinner) findViewById(R.id.spinner2);
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        bendList.getData();
        //adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bendList.data);
        //spinner.setAdapter(adapter);

        setTitle("Rezervisi");

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



        calendar = (CalendarView) findViewById(R.id.calendarView2);
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
    final String[] MobNumber = {"0691050988"};
    public void rez(View view) {
        setTitle(izabraniDatum + bend);
        String imeBenda = bend;
        String datum = izabraniDatum;
        String str_event = event.getText().toString();
        String str_ime = ime.getText().toString();
        String str_mesto = mesto.getText().toString();
        String str_restoran = restoran.getText().toString();
        String type = "rezervisi";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, imeBenda, datum,str_event,str_ime,str_mesto,str_restoran);
                for (int i = 0; i < MobNumber.length; i++) {
                    String tempMobileNumber = MobNumber[i];
                    sendSMS(tempMobileNumber,imeBenda  + ": " +"\n" + str_event +"\n" + datum +"\n"+ str_ime +"\n" + str_restoran +"\n"+  str_mesto+"\n");
                }
                Toast.makeText(Main2Activity.this,"Uspesno dodata rezervacija",Toast.LENGTH_SHORT).show();
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

    public void sendSMS(String phoneNo, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            Toast.makeText(getApplicationContext(), "Sending...",
                    Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), "Message Sent",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),ex.getMessage(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add("Obrisi rezervaciju").setIntent(new Intent(this, Main3Activity.class));
        menu.add("Pregled rezervacija").setIntent(new Intent(this, SpisakBendova.class));
        menu.add("Broj svadbi").setIntent(new Intent(this, Counter.class));
        menu.add("Admin panel").setIntent(new Intent(this, Register.class));
        menu.add("Posalji obavestenje").setIntent(new Intent(this, SmsActivity.class));
        menu.add("Kalendar").setIntent(new Intent(this, SlobodniBendovi.class));
        menu.add("Test baze").setIntent(new Intent(this, TestBaza.class));




        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){

            return true;

        }

        return super.onOptionsItemSelected(item);
    }


}
