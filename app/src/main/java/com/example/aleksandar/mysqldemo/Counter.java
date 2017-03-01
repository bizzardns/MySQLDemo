package com.example.aleksandar.mysqldemo;

import android.content.Intent;
import android.os.StrictMode;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class Counter extends AppCompatActivity {

    ReservationList list = new ReservationList();
    BrojacBendova brojacBendova = new BrojacBendova();
    BrojacDatuma brojacDatuma = new BrojacDatuma();
    BendList bendList = new BendList();
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        setContentView(R.layout.activity_counter);


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
                    Intent myIntent = new Intent(Counter.this, Main2Activity.class);
                    Counter.this.startActivity(myIntent);

                }
                else if (id == R.id.nav2)
                {
                    Intent myIntent = new Intent(Counter.this, Main3Activity.class);
                    Counter.this.startActivity(myIntent);
                }
                else if (id == R.id.nav3)
                {
                    Intent myIntent = new Intent(Counter.this, SlobodniBendovi.class);
                    Counter.this.startActivity(myIntent);

                }
                else if (id == R.id.nav4)
                {
                    Intent myIntent = new Intent(Counter.this, Counter.class);
                    Counter.this.startActivity(myIntent);

                }
                else if (id == R.id.nav5)
                {
                    Intent myIntent = new Intent(Counter.this, SmsActivity.class);
                    Counter.this.startActivity(myIntent);

                }
                else if (id == R.id.nav6)
                {
                    Intent myIntent = new Intent(Counter.this, Register.class);
                    Counter.this.startActivity(myIntent);

                }


                return true;
            }
        } );





        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        bendList.getData();
        list.getData();
        brojacBendova.getData();
        brojacDatuma.getData();


        CustomList ada = new CustomList(Counter.this, brojacBendova.data, brojacDatuma.data);
        int a = list.data.length;
        ListView list = (ListView) findViewById(R.id.lv);
        list.setAdapter(ada);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String val =(String) parent.getItemAtPosition(position);


                //ovde da ubacim logiku

               // Toast.makeText(getApplicationContext(), val,
               //    Toast.LENGTH_SHORT).show();


            }
        });

        setTitle("Ukupan broj svadbi: "+String.valueOf(a));

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Rezervisi").setIntent(new Intent(this, Main2Activity.class));
        menu.add("Obrisi rezervaciju").setIntent(new Intent(this, Main3Activity.class));
        menu.add("Pregled rezervacija").setIntent(new Intent(this, SpisakBendova.class));
        menu.add("Admin panel").setIntent(new Intent(this, Register.class));
        menu.add("Posalji obavestenje").setIntent(new Intent(this, SmsActivity.class));
        menu.add("Kalendar").setIntent(new Intent(this, SlobodniBendovi.class));

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
