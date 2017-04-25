package com.example.aleksandar.mysqldemo;

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
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

public class AllReservations extends AppCompatActivity {




    ContactDB contactBase;
    android.widget.SearchView sv;
    ArrayAdapter<String> adapter;
    ArrayAdapter listAdapter;
EditText editText;
    String[] imeBenda;

    ListView listView;


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

        contactBase = new ContactDB(this, null, 1);
        listView = (ListView) findViewById(R.id.lv);
        ArrayList<String> theList = new ArrayList<>();
        Cursor cursor = contactBase.list_all_list2();
editText = (EditText) findViewById(R.id.editText);
        while (cursor.moveToNext()) {


            theList.add(cursor.getString(3) + " " +cursor.getString(1));

            imeBenda = theList.toArray(new String[0]);

        }
        //listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, imeBenda);
        listAdapter = new ArrayAdapter<String>(this, R.layout.liste, R.id.bendTxt, imeBenda);
        listView.setAdapter(listAdapter);

       // listView.setFilterText(cursor.getString(2));
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

               AllReservations.this.listAdapter.getFilter().filter(s.toString());
                //listView.setTextFilterEnabled(true);
               // listView.setFilterText(s.toString());
                //listView.setFilterText(s.toString().trim());

                listView.setFilterText(s.toString());
                listView.setFilterText(s.toString());



            }

            @Override
            public void afterTextChanged(Editable s) {
                AllReservations.this.listAdapter.getFilter().filter(s);
                listView.setFilterText(s.toString());
                listView.setFilterText(s.toString());
            }
                });



        /*adapter1 = new OfflineListLogic(AllReservations.this, imeBenda, ime, grad, restoran,event);
        listView.setAdapter(adapter1);*/





       /*sv = (android.widget.SearchView) findViewById(R.id.sv);

        sv.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                for(int i = 0; i < l.getAllListViewItems().size(); i++){
                    if (TextUtils.isEmpty(text)) {
                        filter.filter(null);
                    } else {
                        String itemName = ChainageFactory.getAllListViewItems().YourItem();

                        if(itemName.contains(text)){
                            //Perform your operation with filtered data
                        }
                    }
                }
                return true;
            }
        });*/
        //OfflineMode.ListUtils.setDynamicHeight(listView);


    }


}