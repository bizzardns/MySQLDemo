package com.example.aleksandar.mysqldemo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aleksandar.mysqldemo.MySQL.SendReceive;
import com.example.aleksandar.mysqldemo.MySQL.SendReceiveSlobodni;
import com.example.aleksandar.mysqldemo.MySQL.SendRecieveEvent;
import com.example.aleksandar.mysqldemo.SQLiteData.GetEvent;
import com.example.aleksandar.mysqldemo.SQLiteData.GetGrad;
import com.example.aleksandar.mysqldemo.SQLiteData.GetIme;
import com.example.aleksandar.mysqldemo.SQLiteData.GetRestoran;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class OfflineMode extends AppCompatActivity {
    ReservationList list = new ReservationList();
    DatumList datumList = new DatumList();
    GetEvent eventList = new GetEvent();
    GetIme imeList = new GetIme();
    GetGrad gradList = new GetGrad();
    GetRestoran restoranList = new GetRestoran();
    ProgressDialog progress;
    TextView tv;
    String d;
    String n;
    String j;
    String h;
    String l;
    Button sync;
    int counter;
    Spinner sItems;
    String m;
    String s;

    ImageView img;
    ContactDB contactBase;
    android.widget.SearchView sv;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter1;
    ArrayAdapter<String> adapter3;
    CalendarView calendar;
    String date;
    ListView listView;
    ListView listView2;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        setContentView(R.layout.activity_offline_mode);
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
                    Intent myIntent = new Intent(OfflineMode.this, SlobodniBendovi.class);
                    OfflineMode.this.startActivity(myIntent);

                } else if (id == R.id.nav2) {
                    Intent myIntent = new Intent(OfflineMode.this, Main2Activity.class);
                    OfflineMode.this.startActivity(myIntent);
                } else if (id == R.id.nav3) {
                    Intent myIntent = new Intent(OfflineMode.this, Counter.class);
                    OfflineMode.this.startActivity(myIntent);

                } else if (id == R.id.nav4) {
                    Intent myIntent = new Intent(OfflineMode.this, SmsActivity.class);
                    OfflineMode.this.startActivity(myIntent);

                } else if (id == R.id.nav5) {
                    Intent myIntent = new Intent(OfflineMode.this, OfflineMode.class);
                    OfflineMode.this.startActivity(myIntent);

                } else if (id == R.id.nav6) {
                    Intent myIntent = new Intent(OfflineMode.this, Register.class);
                    OfflineMode.this.startActivity(myIntent);

                }


                return true;
            }
        });


        setTitle("Offline mode");
        contactBase = new ContactDB(this, null, 1);

        listView = (ListView) findViewById(R.id.lv);
        listView2 = (ListView) findViewById(R.id.ls);
        img = (ImageView) findViewById(R.id.imageView);
        img.setVisibility(View.VISIBLE);
        calendar = (CalendarView) findViewById(R.id.calendarView);
        listView.setVisibility(View.INVISIBLE);
        listView2.setVisibility(View.INVISIBLE);

        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                img.setVisibility(View.GONE);
                String adate = dayOfMonth + "." + (month + 1) + "." + year + ".";
                s = String.valueOf(dayOfMonth);
                m = String.valueOf((month + 1));

                listView.setVisibility(View.VISIBLE);
                listView2.setVisibility(View.VISIBLE);


                ArrayList<String> theList = new ArrayList<>();
                ArrayList<String> theList2 = new ArrayList<>();
                ArrayList<String> theList3 = new ArrayList<>();
                Cursor cursor = contactBase.list_all_list();

                while (cursor.moveToNext()) {

                    if (cursor.getString(2).equals(adate)) {

                        theList.add(cursor.getString(1) + "\n "+ "\n " + cursor.getString(5)  + "    " + cursor.getString(6)+ "\n " + cursor.getString(4));
                        theList3.add(cursor.getString(1));

                        Collections.sort(theList, new Comparator<String>() {
                            @Override
                            public int compare(String s1, String s2) {
                                return s1.compareToIgnoreCase(s2);
                            }
                        });


                    } else if (!(cursor.getString(2).equals(adate))) {

                        theList2.add(cursor.getString(1));
                        HashSet<String> hashSet = new HashSet<String>();
                        hashSet.addAll(theList2);
                        theList2.clear();
                        theList2.removeAll(theList);
                        theList2.addAll(hashSet);
                        theList2.removeAll(theList3);
                        Collections.sort(theList2, new Comparator<String>() {
                            @Override
                            public int compare(String s1, String s2) {
                                return s1.compareToIgnoreCase(s2);
                            }
                        });


                    }
                    adapter1 = new ArrayAdapter<String>(OfflineMode.this, android.R.layout.simple_list_item_1, theList);
                    listView.setAdapter(adapter1);
                    adapter = new ArrayAdapter<String>(OfflineMode.this, android.R.layout.simple_list_item_1, theList2);
                    listView2.setAdapter(adapter);

                }


            }
        });


        sync = (Button) findViewById(R.id.button6);
        sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress = ProgressDialog.show(OfflineMode.this, "Sinhronizovanje baze podataka!",
                        "Downloading", true);
                counter = 0;
                contactBase.delete();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        list.getData();
                        datumList.getData();
                        imeList.getData();
                        gradList.getData();
                        restoranList.getData();


                        for (int a = 0; a < list.data.length; a++) {
                            counter++;
                            for (int i = 0; i < datumList.data.length; i++) {

                                d = datumList.data[a];
                            }

                            for (int i = 0; i < imeList.data.length; i++) {

                                j = imeList.data[a];
                            }
                            for (int i = 0; i < gradList.data.length; i++) {

                                h = gradList.data[a];
                            }
                            for (int i = 0; i < restoranList.data.length; i++) {

                                l = restoranList.data[a];
                            }


                            contactBase.addContact(list.data[a], d, n, j, h, l);


                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (counter == list.data.length) {

                                    progress.dismiss();
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(OfflineMode.this);
                                    builder1.setMessage("Baza je sinhronizovana!");
                                    builder1.setPositiveButton("Ok",
                                            new DialogInterface.OnClickListener() {

                                                @Override
                                                public void onClick(DialogInterface arg0, int arg1) {

                                                }
                                            });
                                    builder1.show();


                                }
                            }
                        });
                    }
                }).start();

                // Toast.makeText(getApplicationContext(), "Baza je sinhronizovana!", Toast.LENGTH_SHORT).show();


            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_offline, menu);
        return true;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mToggle.onOptionsItemSelected(item)) {

            return true;

        }
        int id = item.getItemId();


        if (id == R.id.sync) {
            sync.performClick();
            // Toast.makeText(getApplicationContext(),"godina", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.today) {
            calendar.setDate(Calendar.getInstance().getTimeInMillis(), false, true);
            Calendar calander = Calendar.getInstance();
            int a = calander.get(Calendar.DAY_OF_MONTH);
            int s = calander.get(Calendar.MONTH) + 1;
            int n = calander.get(Calendar.YEAR);
            d = String.valueOf(a);
            m = String.valueOf(s);
            String y = String.valueOf(n);
            String kurcina = d + "." + m + "." + y + ".";
         /*  lv = (ListView) findViewById(R.id.lv);
            ls = (ListView) findViewById(R.id.ls);*/
            listView.setVisibility(View.VISIBLE);
            listView2.setVisibility(View.VISIBLE);


            ArrayList<String> theList = new ArrayList<>();
            ArrayList<String> theList2 = new ArrayList<>();
            ArrayList<String> theList3 = new ArrayList<>();
            Cursor cursor = contactBase.list_all_list();

            while (cursor.moveToNext()) {

                if (cursor.getString(2).equals(kurcina)) {

                    theList.add(cursor.getString(1) + "\n "+ "\n " + cursor.getString(5)  + "    " + cursor.getString(6)+ "\n " + cursor.getString(4));
                    theList3.add(cursor.getString(1));

                    Collections.sort(theList, new Comparator<String>() {
                        @Override
                        public int compare(String s1, String s2) {
                            return s1.compareToIgnoreCase(s2);
                        }
                    });


                } else if (!(cursor.getString(2).equals(kurcina))) {

                    theList2.add(cursor.getString(1));
                    HashSet<String> hashSet = new HashSet<String>();
                    hashSet.addAll(theList2);
                    theList2.clear();
                    theList2.removeAll(theList);
                    theList2.addAll(hashSet);
                    theList2.removeAll(theList3);
                    Collections.sort(theList2, new Comparator<String>() {
                        @Override
                        public int compare(String s1, String s2) {
                            return s1.compareToIgnoreCase(s2);
                        }
                    });


                }
                adapter1 = new ArrayAdapter<String>(OfflineMode.this, android.R.layout.simple_list_item_1, theList);
                listView.setAdapter(adapter1);
                adapter = new ArrayAdapter<String>(OfflineMode.this, android.R.layout.simple_list_item_1, theList2);
                listView2.setAdapter(adapter);

            }


            //sync.performClick();
            // Toast.makeText(getApplicationContext(),"godina", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.spinner) {
            //sync.performClick();
            // Toast.makeText(getApplicationContext(),"godina", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}