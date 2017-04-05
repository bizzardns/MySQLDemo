package com.example.aleksandar.mysqldemo;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aleksandar.mysqldemo.MySQL.SendReceive;
import com.example.aleksandar.mysqldemo.MySQL.SendReceiveSlobodni;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;


public class SlobodniBendovi extends AppCompatActivity {

    public static String sharedValue = null;
    public static String dan = null;
    public static String mesec = null;
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
    String kurac;
    ScrollView sw;
    Spinner sItems;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigationView;


    String urlAdress = " http://lp-developers.com/freebands.php";
    String urlAdress_reserved = " http://lp-developers.com/reservedOnDate.php";
    /*String urlAdress = "http://macakmisamuzika.com/android/freebands.php";
    String urlAdress_reserved = "http://macakmisamuzika.com/android/reservedOnDate.php";*/


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        setContentView(R.layout.activity_slobodni_bendovi);
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
                    Intent myIntent = new Intent(SlobodniBendovi.this, SlobodniBendovi.class);
                    SlobodniBendovi.this.startActivity(myIntent);
                    SlobodniBendovi.sharedValue = null;


                } else if (id == R.id.nav2) {
                    Intent myIntent = new Intent(SlobodniBendovi.this, Main2Activity.class);
                    SlobodniBendovi.this.startActivity(myIntent);
                    SlobodniBendovi.sharedValue = null;
                } else if (id == R.id.nav3) {
                    Intent myIntent = new Intent(SlobodniBendovi.this, Counter.class);
                    SlobodniBendovi.this.startActivity(myIntent);
                    SlobodniBendovi.sharedValue = null;

                } else if (id == R.id.nav4) {
                    Intent myIntent = new Intent(SlobodniBendovi.this, SmsActivity.class);
                    SlobodniBendovi.this.startActivity(myIntent);
                    SlobodniBendovi.sharedValue = null;

                } else if (id == R.id.nav5) {
                    Intent myIntent = new Intent(SlobodniBendovi.this, OfflineMode.class);
                    SlobodniBendovi.this.startActivity(myIntent);
                    SlobodniBendovi.sharedValue = null;

                } else if (id == R.id.nav6) {
                    Intent myIntent = new Intent(SlobodniBendovi.this, Register.class);
                    SlobodniBendovi.this.startActivity(myIntent);
                    SlobodniBendovi.sharedValue = null;

                }


                return true;

            }
        });


        if (SlobodniBendovi.sharedValue == null) {
            Calendar calander = Calendar.getInstance();
            int a = calander.get(Calendar.DAY_OF_MONTH);
            int s = calander.get(Calendar.MONTH) + 1;
            int n = calander.get(Calendar.YEAR);
            d = String.valueOf(a);
            m = String.valueOf(s);
           SlobodniBendovi.dan = d;
            SlobodniBendovi.mesec = m;
            y = String.valueOf(n);
            String danasnji_datum = d + "." + m + "." + y + ".";
            calendar = (CalendarView) findViewById(R.id.calendarView);
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            lv = (ListView) findViewById(R.id.lv);
            ls = (ListView) findViewById(R.id.ls);
            setTitle("");
            sw = (ScrollView) findViewById(R.id.sw);
            SendReceiveSlobodni pr = new SendReceiveSlobodni(urlAdress, SlobodniBendovi.this, danasnji_datum, ls);
            pr.execute();
            SendReceive sr = new SendReceive(urlAdress_reserved, SlobodniBendovi.this, danasnji_datum, lv);
            sr.execute();
            SlobodniBendovi.sharedValue = danasnji_datum;


        } else {
            calendar = (CalendarView) findViewById(R.id.calendarView);
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            d = CustomListEvent.dan_custom;
            m = CustomListEvent.mesec_custom;
            lv = (ListView) findViewById(R.id.lv);
            ls = (ListView) findViewById(R.id.ls);
            setTitle("");
            sw = (ScrollView) findViewById(R.id.sw);
            try {
                calendar.setDate(new SimpleDateFormat("dd.MM.yyyy.").parse(SlobodniBendovi.sharedValue).getTime(), false, true);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // Toast.makeText(getApplicationContext(),kurcina, Toast.LENGTH_SHORT).show();
            SendReceiveSlobodni pr = new SendReceiveSlobodni(urlAdress, SlobodniBendovi.this, SlobodniBendovi.sharedValue, ls);
            pr.execute();
            SendReceive sr = new SendReceive(urlAdress_reserved, SlobodniBendovi.this, SlobodniBendovi.sharedValue, lv);
            sr.execute();

        }





       /* lv.setVisibility(View.INVISIBLE);
        ls.setVisibility(View.INVISIBLE);*/
    /*    lv = (ListView) findViewById(R.id.lv);
        ls = (ListView) findViewById(R.id.ls);*/
        lv.setFocusable(false);
        ls.setFocusable(false);


        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // img.setVisibility(View.GONE);

                ls.setVisibility(View.VISIBLE);
                lv.setVisibility(View.VISIBLE);

                date = dayOfMonth + "." + (month + 1) + "." + year + ".";
                SlobodniBendovi.sharedValue = date;
                d = String.valueOf(dayOfMonth);
                m = String.valueOf((month + 1));
                SlobodniBendovi.dan = d;
                SlobodniBendovi.mesec = m;
                SendReceiveSlobodni pr = new SendReceiveSlobodni(urlAdress, SlobodniBendovi.this, date, ls);
                pr.execute();
                SendReceive sr = new SendReceive(urlAdress_reserved, SlobodniBendovi.this, date, lv);
                sr.execute();

                //OVDE SE BIRA DATUM POMOCU KOJEG SE DOBAVLJAJU IZ BAZE SLOBODNI BENDOVI!

            }
        });


        final ArrayList<String> theList = new ArrayList<>();
        theList.add("2017");
        theList.add("2018");
        theList.add("2019");
        theList.add("2020");
        theList.add("2021");
        theList.add("2022");
        theList.add("2023");
        theList.add("2024");
      /*  izabraniDatum = CustomListEvent.dva;
        selectedDate = UpisIzKalendara.dva;*/
        sItems = (Spinner) findViewById(R.id.spinner2);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, theList);

        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sItems.setAdapter(adapter);





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
            sw.smoothScrollTo(0, 0);
            //Toast.makeText(getApplicationContext(),date, Toast.LENGTH_SHORT).show();
            calendar.setDate(Calendar.getInstance().getTimeInMillis(), false, true);
            Calendar calander = Calendar.getInstance();
            int a = calander.get(Calendar.DAY_OF_MONTH);
            int s = calander.get(Calendar.MONTH) + 1;
            int n = calander.get(Calendar.YEAR);
            d = String.valueOf(a);
            m = String.valueOf(s);
            y = String.valueOf(n);
            SlobodniBendovi.dan = d;
            SlobodniBendovi.mesec = m;
            String kurcina = d + "." + m + "." + y + ".";
         /*  lv = (ListView) findViewById(R.id.lv);
            ls = (ListView) findViewById(R.id.ls);*/
            ls.setVisibility(View.VISIBLE);
            lv.setVisibility(View.VISIBLE);

            // Toast.makeText(getApplicationContext(),kurcina, Toast.LENGTH_SHORT).show();
            SendReceiveSlobodni pr = new SendReceiveSlobodni(urlAdress, SlobodniBendovi.this, kurcina, ls);
            pr.execute();
            SendReceive sr = new SendReceive(urlAdress_reserved, SlobodniBendovi.this, kurcina, lv);
            sr.execute();


            return true;
        }
        if (id == R.id.spinner) {
            sw.smoothScrollTo(0, 0);
            sItems.performClick();

            sItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    kurac = sItems.getSelectedItem().toString();
                    //date = d + "." + m + "." + kurac + ".";
                     d = SlobodniBendovi.dan;
                      m = SlobodniBendovi.mesec;
                    date = d + "." + m + "." + kurac + ".";

                    try {
                        calendar.setDate(new SimpleDateFormat("dd.MM.yyyy.").parse(date).getTime(), false, true);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    ls.setVisibility(View.VISIBLE);
                    lv.setVisibility(View.VISIBLE);

                    SendReceiveSlobodni pr1 = new SendReceiveSlobodni(urlAdress, SlobodniBendovi.this, date, ls);
                    pr1.execute();

                    SendReceive sr1 = new SendReceive(urlAdress_reserved, SlobodniBendovi.this, date, lv);
                    sr1.execute();
                    SlobodniBendovi.sharedValue = date;
                    //Toast.makeText(getApplicationContext(),date, Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {




                }
            });












            // Toast.makeText(getApplicationContext(),kurac, Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.sync) {

            ls.setVisibility(View.VISIBLE);
            lv.setVisibility(View.VISIBLE);
            try {
                calendar.setDate(new SimpleDateFormat("dd.MM.yyyy.").parse(SlobodniBendovi.sharedValue).getTime(), false, true);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // Toast.makeText(getApplicationContext(),kurcina, Toast.LENGTH_SHORT).show();
            SendReceiveSlobodni pr = new SendReceiveSlobodni(urlAdress, SlobodniBendovi.this, SlobodniBendovi.sharedValue, ls);
            pr.execute();
            SendReceive sr = new SendReceive(urlAdress_reserved, SlobodniBendovi.this, SlobodniBendovi.sharedValue, lv);
            sr.execute();


            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public static class ListUtils {
        public static void setDynamicHeight(ListView mListView) {
            ListAdapter mListAdapter = mListView.getAdapter();
            if (mListAdapter == null) {
                // when adapter is null
                return;
            }
            int height = 0;
            int desiredWidth = View.MeasureSpec.makeMeasureSpec(mListView.getWidth(), View.MeasureSpec.UNSPECIFIED);
            for (int i = 0; i < mListAdapter.getCount(); i++) {
                View listItem = mListAdapter.getView(i, null, mListView);
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                height += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = mListView.getLayoutParams();
            params.height = height + (mListView.getDividerHeight() * (mListAdapter.getCount() - 1));
            mListView.setLayoutParams(params);
            mListView.requestLayout();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent inMain = new Intent(SlobodniBendovi.this, MenuActivity.class);
        inMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(inMain);
    }

}

 /* ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, theList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner sItems = (Spinner) findViewById(R.id.spinnerBand);
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
        });*/





        /*lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                 long_click = (String) parent.getItemAtPosition(position);
                brisi();
               // Toast.makeText(getApplicationContext(), long_click +" "+ date, Toast.LENGTH_SHORT).show();

                return false;
            }


        });*/

       /* ls.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

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


        });*/

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
        });
    }*/
         /* ArrayList<String> theList = new ArrayList<>();
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
        sItems = (Spinner) findViewById(R.id.spinner2);
        sItems.setAdapter(adapter);

        sItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                y = sItems.getSelectedItem().toString();

                Toast.makeText(getApplicationContext(),selectedDate, Toast.LENGTH_SHORT).show();

                selectedDate = d+"/"+ m +"/"+ y;
                try {
                    calendar.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(selectedDate).getTime(), false, true);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {



            }
        });*/