package com.example.aleksandar.mysqldemo;

import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;


import com.example.aleksandar.mysqldemo.SearchFragment.PagerAdapter;

import java.util.ArrayList;

public class AllReservations extends AppCompatActivity {




    ContactDB contactBase;
    ArrayAdapter<String> adapter;
    ArrayAdapter listAdapter;
    String[] imeBenda;
    SearchView searchView;
    ListView listView;
    AlertDialog alertDialog;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigationView;

    public void checkNetworkConnection() {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setTitle("No internet Connection");
        builder.setMessage("Please turn on internet connection to continue");
        builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        android.support.v7.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public boolean isNetworkConnectionAvailable() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();
        if (isConnected) {
            Log.d("Network", "Connected");
            return true;
        } else {
            checkNetworkConnection();
            Log.d("Network", "Not Connected");
            return false;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        setContentView(R.layout.activity_all_reservations);
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
                    Intent myIntent = new Intent(AllReservations.this, SlobodniBendovi.class);
                    AllReservations.this.startActivity(myIntent);

                } else if (id == R.id.nav2) {
                    Intent myIntent = new Intent(AllReservations.this, Main2Activity.class);
                    AllReservations.this.startActivity(myIntent);
                } else if (id == R.id.nav3) {
                    Intent myIntent = new Intent(AllReservations.this, Counter.class);
                    AllReservations.this.startActivity(myIntent);

                } else if (id == R.id.nav4) {
                    Intent myIntent = new Intent(AllReservations.this, SmsActivity.class);
                    AllReservations.this.startActivity(myIntent);

                } else if (id == R.id.nav5) {
                    Intent myIntent = new Intent(AllReservations.this, OfflineMode.class);
                    AllReservations.this.startActivity(myIntent);

                } else if (id == R.id.nav6) {
                    Intent myIntent = new Intent(AllReservations.this, Register.class);
                    AllReservations.this.startActivity(myIntent);

                }


                return true;
            }
        });

        setTitle("");




        contactBase = new ContactDB(this, null, 1);



        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("GRAD").setIcon(R.drawable.grad_dva));
        tabLayout.addTab(tabLayout.newTab().setText("LOKAL").setIcon(R.drawable.restoran));
        tabLayout.addTab(tabLayout.newTab().setText("IME").setIcon(R.drawable.imena));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);


        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);



        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()){
                    case 0:
                            Tab1();
                        break;
                    case 1:
                        Tab2();

                        break;

                    case 2:

                        Tab3();
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()){
                    case 0:
                        Tab1();
                        break;
                    case 1:
                        Tab2();

                        break;

                    case 2:

                        Tab3();
                        break;
                }
            }
        });















        /*contactBase = new ContactDB(this, null, 1);
        listView = (ListView) findViewById(R.id.lv1);
        ArrayList<String> theList = new ArrayList<>();
        Cursor cursor = contactBase.list_all_list2();

        while (cursor.moveToNext()) {


            theList.add(cursor.getString(4) + "\n" +cursor.getString(1) +"\n"+ cursor.getString(2)+"\n"+ cursor.getString(5));

            imeBenda = theList.toArray(new String[0]);

        }

        //listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, imeBenda);
        listAdapter = new ArrayAdapter<String>(this, R.layout.liste, R.id.bendTxt, imeBenda);
        listView.setAdapter(listAdapter);*/




    }


    public void Tab1 (){
        listView = (ListView) findViewById(R.id.lv1);
        ArrayList<String> theList = new ArrayList<>();
        Cursor cursor = contactBase.list_all_list2();

        while (cursor.moveToNext()) {


            theList.add(cursor.getString(4) + "\n" +cursor.getString(1) +"\n"+ cursor.getString(2)+"\n"+ cursor.getString(5)+"\n"+ cursor.getString(3)+"  " +cursor.getString(6));

            imeBenda = theList.toArray(new String[0]);

        }

        // listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, imeBenda);
        listAdapter = new ArrayAdapter<String>(this, R.layout.search_liste, R.id.bendTxt, imeBenda);
        listView.setAdapter(listAdapter);

    }
    public void Tab2 (){
        listView = (ListView) findViewById(R.id.lv2);
        ArrayList<String> theList = new ArrayList<>();
        Cursor cursor = contactBase.list_all_list2();

        while (cursor.moveToNext()) {


            theList.add(cursor.getString(5) + "\n" +cursor.getString(1) +"\n"+ cursor.getString(2)+"\n"+ cursor.getString(4)+"\n"+ cursor.getString(3)+"  " +cursor.getString(6));

            imeBenda = theList.toArray(new String[0]);

        }

        // listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, imeBenda);
        listAdapter = new ArrayAdapter<String>(this, R.layout.search_liste, R.id.bendTxt, imeBenda);
        listView.setAdapter(listAdapter);

    }
    public void Tab3 (){
        listView = (ListView) findViewById(R.id.lv3);
        ArrayList<String> theList = new ArrayList<>();
        Cursor cursor = contactBase.list_all_list2();

        while (cursor.moveToNext()) {


            theList.add(cursor.getString(3) + "\n" +cursor.getString(1) +"\n"+ cursor.getString(2)+"\n"+ cursor.getString(5)+"\n"+ cursor.getString(4)+"  " +cursor.getString(6));

            imeBenda = theList.toArray(new String[0]);

        }

        // listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, imeBenda);
        listAdapter = new ArrayAdapter<String>(this, R.layout.search_liste, R.id.bendTxt, imeBenda);
        listView.setAdapter(listAdapter);

    }



    @Override
    public boolean onCreateOptionsMenu( Menu menu) {
        getMenuInflater().inflate( R.menu.menu_main2, menu);

        MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                if (listAdapter == null){
                    alertDialog = new AlertDialog.Builder(AllReservations.this).create();

                    alertDialog.setMessage("Izaberite kategoriju pretrage!");
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                    //Toast.makeText(AllReservations.this, "", Toast.LENGTH_SHORT).show();

                }else{

                    AllReservations.this.listAdapter.getFilter().filter(s);

                }


                return false;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mToggle.onOptionsItemSelected(item)) {

            return true;

        }
        int id = item.getItemId();





        return super.onOptionsItemSelected(item);
    }


}