package com.example.aleksandar.mysqldemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;

import com.example.aleksandar.mysqldemo.MySQL.SendReceive;
import com.example.aleksandar.mysqldemo.MySQL.SendReceiveCount;
import com.example.aleksandar.mysqldemo.MySQL.SendReceiveSlobodni;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class Counter extends AppCompatActivity {


    Spinner sItems;
    String godina;
    String y;
    ReservationList list = new ReservationList();
    BrojacBendova brojacBendova = new BrojacBendova();
    BrojacDatuma brojacDatuma = new BrojacDatuma();
    BendList bendList = new BendList();
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigationView;
    String adress = "http://macakmisamuzika.com/android/counter.php";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        setContentView(R.layout.activity_counter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        mNavigationView = (NavigationView) findViewById(R.id.nav_item);
        mNavigationView.setItemIconTintList(null);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();
                if (id == R.id.nav1) {
                    Intent myIntent = new Intent(Counter.this, SlobodniBendovi.class);
                    Counter.this.startActivity(myIntent);

                } else if (id == R.id.nav2) {
                    Intent myIntent = new Intent(Counter.this, Main2Activity.class);
                    Counter.this.startActivity(myIntent);
                } else if (id == R.id.nav3) {
                    Intent myIntent = new Intent(Counter.this, Counter.class);
                    Counter.this.startActivity(myIntent);

                } else if (id == R.id.nav4) {
                    Intent myIntent = new Intent(Counter.this, SmsActivity.class);
                    Counter.this.startActivity(myIntent);

                } else if (id == R.id.nav5) {
                    Intent myIntent = new Intent(Counter.this, OfflineMode.class);
                    Counter.this.startActivity(myIntent);

                } else if (id == R.id.nav6) {
                    Intent myIntent = new Intent(Counter.this, Register.class);
                    Counter.this.startActivity(myIntent);

                }

                return true;
            }
        });

        Calendar calander = Calendar.getInstance();
        int n = calander.get(Calendar.YEAR);

      y = String.valueOf(n);
        /*  list.getData();
        int a = list.data.length;*/

        setTitle(y+".");



        ArrayList<String> theList = new ArrayList<>();
        theList.add("2017");
        theList.add("2018");
        theList.add("2019");
        theList.add("2020");
        theList.add("2021");
        theList.add("2022");
        theList.add("2023");
        theList.add("2024");


        sItems = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, theList);

        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sItems.setAdapter(adapter);

        sItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                y = sItems.getSelectedItem().toString();
                 setTitle(y+".");
                ListView lv = (ListView) findViewById(R.id.lv);
                SendReceiveCount sendReceiveCount = new SendReceiveCount(adress,Counter.this,"%"+y+"%",lv);
                sendReceiveCount.execute();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {


            }
        });


        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        ListView lv = (ListView) findViewById(R.id.lv);
        SendReceiveCount sendReceiveCount = new SendReceiveCount(adress,this,"%"+y+"%",lv);
        sendReceiveCount.execute();


















        //bendList.getData();
       //list.getData();
        //brojacBendova.getData();
        //brojacDatuma.getData();

      /*  CustomList ada = new CustomList(Counter.this, brojacDatuma.data, brojacDatuma.data);

        ListView list = (ListView) findViewById(R.id.lv);
        list.setAdapter(ada);*/

      /*  list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String val = (String) parent.getItemAtPosition(position);


                //ovde da ubacim logiku

                // Toast.makeText(getApplicationContext(), val,
                //    Toast.LENGTH_SHORT).show();


            }
        });*/


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_counter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mToggle.onOptionsItemSelected(item)) {

            return true;

        }
        int id = item.getItemId();



        if (id == R.id.spinner) {
            sItems.performClick();
            // Toast.makeText(getApplicationContext(),"godina", Toast.LENGTH_SHORT).show();
            return true;
        }












        return super.onOptionsItemSelected(item);
    }
}
