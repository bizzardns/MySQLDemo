package com.example.aleksandar.mysqldemo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.icu.util.Calendar;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    GetIme imeList = new GetIme();
    GetGrad gradList = new GetGrad();
    GetRestoran restoranList = new GetRestoran();
    GetEvent eventList = new GetEvent();
    ProgressDialog progress;
    String year_date;
    String d;
    String datum;
    String j;
    String h;
    String l;
    String y;
    int counter;
    Spinner sItems;
    String m;
    ScrollView sw;
    ContactDB contactBase;
    ArrayAdapter<String> adapter;
    String[] imeBenda;
    String[] imeBenda2;
    String[] ime;
    String[] grad;
    String[] restoran;
    String[] event;
    CalendarView calendar;
    ListView listView;
    ListView listView2;
    TextView textView;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigationView;
    public void checkNetworkConnection(){
        android.support.v7.app.AlertDialog.Builder builder =new android.support.v7.app.AlertDialog.Builder(this);
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

    public boolean isNetworkConnectionAvailable(){
        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();
        if(isConnected) {
            Log.d("Network", "Connected");
            return true;
        }
        else{
            checkNetworkConnection();
            Log.d("Network","Not Connected");
            return false;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        setContentView(R.layout.offline_mode_test);
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
                   /* Intent myIntent = new Intent(OfflineMode.this, Main2Activity.class);
                    OfflineMode.this.startActivity(myIntent);*/
                    Toast.makeText(getApplicationContext(), "Ova opcija Vam nije dostupna!",
                            Toast.LENGTH_SHORT).show();
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
                    /*Intent myIntent = new Intent(OfflineMode.this, Register.class);
                    OfflineMode.this.startActivity(myIntent);*/
                    Toast.makeText(getApplicationContext(), "Ova opcija Vam nije dostupna!",
                            Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        setTitle("");
        sw = (ScrollView) findViewById(R.id.sw);

        contactBase = new ContactDB(this, null, 1);
        calendar = (CalendarView) findViewById(R.id.calendarOffline);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        sItems = (Spinner) findViewById(R.id.spinner2);
        listView = (ListView) findViewById(R.id.lv);
        listView2 = (ListView) findViewById(R.id.ls);
        textView = (TextView) findViewById(R.id.textView6);
        datum = MenuActivity.sharedValue;
        d = MenuActivity.dan;
        m = MenuActivity.mesec;
        try {
            calendar.setDate(new SimpleDateFormat("dd.MM.yyyy.").parse(datum).getTime(), false, true);
            ArrayList<String> theList = new ArrayList<>();
            ArrayList<String> theList2 = new ArrayList<>();
            ArrayList<String> theList3 = new ArrayList<>();
            ArrayList<String> theList4 = new ArrayList<>();
            ArrayList<String> theList5 = new ArrayList<>();
            ArrayList<String> theList6 = new ArrayList<>();
            ArrayList<String> theList7 = new ArrayList<>();
            Cursor cursor = contactBase.list_all_list();

            while (cursor.moveToNext()) {

                if (cursor.getString(2).equals(datum)) {

                    theList.add(cursor.getString(1));

                    theList4.add(cursor.getString(3));

                    theList5.add(cursor.getString(4));

                    theList6.add(cursor.getString(5));
                    theList7.add(cursor.getString(6));

                    theList3.add(cursor.getString(1));

                } else if (!(cursor.getString(2).equals(datum) || cursor.getString(2).isEmpty() )) {

                    theList2.add(cursor.getString(1));

                }

            }

            if (theList.isEmpty() && theList2.isEmpty()) {

                textView.setVisibility(View.VISIBLE);

            } else {

                textView.setVisibility(View.GONE);
            }

            if (theList.isEmpty()) {
                listView.setVisibility(View.GONE);
            } else {
                imeBenda = theList.toArray(new String[0]);
                ime = theList4.toArray(new String[0]);
                grad = theList5.toArray(new String[0]);
                restoran = theList6.toArray(new String[0]);
                event = theList7.toArray(new String[0]);
                OfflineListLogic adapter1 = new OfflineListLogic(OfflineMode.this, imeBenda, ime, grad, restoran,event);
                listView.setAdapter(adapter1);
            }
            if (theList2.isEmpty()) {
                listView2.setVisibility(View.GONE);

            } else {
                HashSet<String> hashSet = new HashSet<String>();
                hashSet.addAll(theList2);
                theList2.clear();
                //theList2.removeAll(theList);
                theList2.addAll(hashSet);
                //theList2.retainAll(theList);
                theList2.removeAll(theList);
                Collections.sort(theList2, new Comparator<String>() {
                    @Override
                    public int compare(String s1, String s2) {
                        return s1.compareToIgnoreCase(s2);
                    }
                });
                imeBenda2 = theList2.toArray(new String[0]);
                OfflineSlobodniLogic adapter = new OfflineSlobodniLogic(OfflineMode.this, imeBenda2);
                listView2.setAdapter(adapter);
            }

            ListUtils.setDynamicHeight(listView2);
            ListUtils.setDynamicHeight(listView);

            listView = (ListView) findViewById(R.id.lv);
            listView2 = (ListView) findViewById(R.id.ls);

            listView.setFocusable(false);
            listView2.setFocusable(false);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                datum = dayOfMonth + "." + (month + 1) + "." + year + ".";
                d = String.valueOf(dayOfMonth);
                m = String.valueOf((month + 1));

                listView.setVisibility(View.VISIBLE);
                listView2.setVisibility(View.VISIBLE);

                ArrayList<String> theList = new ArrayList<>();
                ArrayList<String> theList2 = new ArrayList<>();
                ArrayList<String> theList3 = new ArrayList<>();
                ArrayList<String> theList4 = new ArrayList<>();
                ArrayList<String> theList5 = new ArrayList<>();
                ArrayList<String> theList6 = new ArrayList<>();
                ArrayList<String> theList7 = new ArrayList<>();

                Cursor cursor = contactBase.list_all_list();

                while (cursor.moveToNext()) {

                    if (cursor.getString(2).equals(datum)) {

                        theList.add(cursor.getString(1));
                        theList4.add(cursor.getString(3));
                        theList5.add(cursor.getString(4));
                        theList6.add(cursor.getString(5));
                        theList7.add(cursor.getString(6));
                        theList3.add(cursor.getString(1));


                    } else if (!(cursor.getString(2).equals(datum) || cursor.getString(2).isEmpty() )) {

                        theList2.add(cursor.getString(1));
                    }
                }
                if (theList.isEmpty() && theList2.isEmpty()) {

                    textView.setVisibility(View.VISIBLE);

                } else {

                    textView.setVisibility(View.GONE);

                }

                if (theList.isEmpty()) {
                    listView.setVisibility(View.GONE);
                    /*Toast.makeText(getApplicationContext(), "Svi bendovi su slobodni!",
                            Toast.LENGTH_SHORT).show();*/
                } else {
                    imeBenda = theList.toArray(new String[0]);
                    ime = theList4.toArray(new String[0]);
                    grad = theList5.toArray(new String[0]);
                    restoran = theList6.toArray(new String[0]);
                    event = theList7.toArray(new String[0]);
                    OfflineListLogic adapter1 = new OfflineListLogic(OfflineMode.this, imeBenda, ime, grad, restoran,event);
                    listView.setAdapter(adapter1);
                }
                if (theList2.isEmpty()) {
                    listView2.setVisibility(View.GONE);
                    /*Toast.makeText(getApplicationContext(), "Svi bendovi su zauzeti!",
                            Toast.LENGTH_SHORT).show();*/

                } else {
                    HashSet<String> hashSet = new HashSet<String>();
                    hashSet.addAll(theList2);
                    theList2.clear();
                    //theList2.removeAll(theList);
                    theList2.addAll(hashSet);
                    //theList2.retainAll(theList);
                    theList2.removeAll(theList);
                    Collections.sort(theList2, new Comparator<String>() {
                        @Override
                        public int compare(String s1, String s2) {
                            return s1.compareToIgnoreCase(s2);
                        }
                    });
                    imeBenda2 = theList2.toArray(new String[0]);
                    OfflineSlobodniLogic adapter = new OfflineSlobodniLogic(OfflineMode.this, imeBenda2);
                    // ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, imeBenda2);
                    listView2.setAdapter(adapter);
                }
                ListUtils.setDynamicHeight(listView2);
                ListUtils.setDynamicHeight(listView);

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
            if (isNetworkConnectionAvailable() == true) {
               // sync.performClick();
                progress = ProgressDialog.show(OfflineMode.this, "Sinhronizovanje baze podataka!",
                        "Downloading...", true);

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
                        eventList.getData();


                        for (int a = 0; a < list.data.length; a++) {
                            counter++;

                            d = datumList.data[a];

                            j = imeList.data[a];

                            h = gradList.data[a];

                            l = restoranList.data[a];

                            y = eventList.data[a];

                            contactBase.addContact(list.data[a],d,j,h,l,y);

                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (counter == list.data.length) {
                                   // Toast.makeText(getApplicationContext(),String.valueOf(counter),Toast.LENGTH_SHORT).show();
                                    progress.dismiss();

                                    try {
                                        calendar.setDate(Calendar.getInstance().getTimeInMillis(), false, true);
                                        Calendar calander = Calendar.getInstance();
                                        int a = calander.get(Calendar.DAY_OF_MONTH);
                                        int s = calander.get(Calendar.MONTH) + 1;
                                        int n = calander.get(Calendar.YEAR);
                                        d = String.valueOf(a);
                                        m = String.valueOf(s);
                                        String y = String.valueOf(n);
                                        String kurcina = d + "." + m + "." + y + ".";
                                        listView.setVisibility(View.VISIBLE);
                                        listView2.setVisibility(View.VISIBLE);
                                        calendar.setDate(new SimpleDateFormat("dd.MM.yyyy.").parse(kurcina).getTime(), false, true);
                                        ArrayList<String> theList = new ArrayList<>();
                                        ArrayList<String> theList2 = new ArrayList<>();
                                        ArrayList<String> theList3 = new ArrayList<>();
                                        ArrayList<String> theList4 = new ArrayList<>();
                                        ArrayList<String> theList5 = new ArrayList<>();
                                        ArrayList<String> theList6 = new ArrayList<>();
                                        ArrayList<String> theList7 = new ArrayList<>();
                                        Cursor cursor = contactBase.list_all_list();

                                        while (cursor.moveToNext()) {

                                            if (cursor.getString(2).equals(kurcina)) {

                                                theList.add(cursor.getString(1));

                                                theList4.add(cursor.getString(3));

                                                theList5.add(cursor.getString(4));

                                                theList6.add(cursor.getString(5));
                                                theList7.add(cursor.getString(6));

                                                theList3.add(cursor.getString(1));


                                            } else if (!(cursor.getString(2).equals(kurcina) || cursor.getString(2).isEmpty() )) {

                                                theList2.add(cursor.getString(1));

                                            }

                                        }

                                        if (theList.isEmpty() && theList2.isEmpty()) {

                                            textView.setVisibility(View.VISIBLE);

                                        } else {

                                            textView.setVisibility(View.GONE);

                                        }

                                        if (theList.isEmpty()) {
                                            listView.setVisibility(View.GONE);
                                        } else {
                                            imeBenda = theList.toArray(new String[0]);
                                            ime = theList4.toArray(new String[0]);
                                            grad = theList5.toArray(new String[0]);
                                            restoran = theList6.toArray(new String[0]);
                                            event = theList7.toArray(new String[0]);
                                            imeBenda2 = theList2.toArray(new String[0]);
                                            OfflineListLogic adapter1 = new OfflineListLogic(OfflineMode.this, imeBenda, ime, grad, restoran,event);
                                            listView.setAdapter(adapter1);
                                        }
                                        if (theList2.isEmpty()) {
                                            listView2.setVisibility(View.GONE);

                                        } else {
                                            HashSet<String> hashSet = new HashSet<String>();
                                            hashSet.addAll(theList2);
                                            theList2.clear();
                                            theList2.addAll(hashSet);
                                            theList2.removeAll(theList);
                                            Collections.sort(theList2, new Comparator<String>() {
                                                @Override
                                                public int compare(String s1, String s2) {
                                                    return s1.compareToIgnoreCase(s2);
                                                }
                                            });
                                            imeBenda2 = theList2.toArray(new String[0]);
                                            OfflineSlobodniLogic adapter = new OfflineSlobodniLogic(OfflineMode.this, imeBenda2);
                                            listView2.setAdapter(adapter);
                                        }

                                        ListUtils.setDynamicHeight(listView2);
                                        ListUtils.setDynamicHeight(listView);

                                        listView = (ListView) findViewById(R.id.lv);
                                        listView2 = (ListView) findViewById(R.id.ls);

                                        listView.setFocusable(false);
                                        listView2.setFocusable(false);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }

                                     contactBase.close();
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
            }


            return true;
        }
        if (id == R.id.search) {
            Intent intent = new Intent(getApplicationContext(), AllReservations.class);
            startActivity(intent);
        }
        if (id == R.id.today) {
            finish();
            startActivity(getIntent());
            return true;

        }
        if (id == R.id.spinner) {
            sw.smoothScrollTo(0, 0);

            return true;
        }
        if(id == R.id.action_menu1){

            year_date = d + "." + m + "." + "2017" + ".";

            changeYear();

            return true;
        }
        if(id == R.id.action_menu2){

            year_date = d + "." + m + "." + "2018" + ".";

            changeYear();

            return true;
        }
        if(id == R.id.action_menu3){

            year_date = d + "." + m + "." + "2019" + ".";

            changeYear();

            return true;
        }
        if(id == R.id.action_menu4){

            year_date = d + "." + m + "." + "2020" + ".";

            changeYear();

            return true;
        }
        if(id == R.id.action_menu5){

            year_date = d + "." + m + "." + "2021" + ".";

            changeYear();

            return true;
        }
        if(id == R.id.action_menu6){

            year_date = d + "." + m + "." + "2022" + ".";

            changeYear();

            return true;
        }
        if(id == R.id.action_menu7){

            year_date = d + "." + m + "." + "2023" + ".";

            changeYear();

            return true;
        }
        if(id == R.id.action_menu8){

            year_date = d + "." + m + "." + "2024" + ".";

            changeYear();

            return true;
        }
        if(id == R.id.action_menu9){

            year_date = d + "." + m + "." + "2025" + ".";

            changeYear();

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
        Intent inMain = new Intent(OfflineMode.this, MenuActivity.class);
        inMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(inMain);
    }

    public void changeYear(){

        try {
            calendar.setDate(new SimpleDateFormat("dd.MM.yyyy.").parse(year_date).getTime(), false, true);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        listView.setVisibility(View.VISIBLE);
        listView2.setVisibility(View.VISIBLE);


        ArrayList<String> theList = new ArrayList<>();
        ArrayList<String> theList2 = new ArrayList<>();
        ArrayList<String> theList3 = new ArrayList<>();
        ArrayList<String> theList4 = new ArrayList<>();
        ArrayList<String> theList5 = new ArrayList<>();
        ArrayList<String> theList6 = new ArrayList<>();
        ArrayList<String> theList7 = new ArrayList<>();

        Cursor cursor = contactBase.list_all_list();

        while (cursor.moveToNext()) {

            if (cursor.getString(2).equals(year_date)) {

                theList.add(cursor.getString(1));
                theList4.add(cursor.getString(3));
                theList5.add(cursor.getString(4));
                theList6.add(cursor.getString(5));
                theList7.add(cursor.getString(6));
                theList3.add(cursor.getString(1));

            } else if(!(cursor.getString(2).equals(year_date) || cursor.getString(2).isEmpty() )) {

                theList2.add(cursor.getString(1));

            }

        }
        if (theList.isEmpty() && theList2.isEmpty()) {

            textView.setVisibility(View.VISIBLE);

        } else {

            textView.setVisibility(View.GONE);


        }
        if (theList.isEmpty()) {
            listView.setVisibility(View.GONE);
        } else {
            imeBenda = theList.toArray(new String[0]);
            ime = theList4.toArray(new String[0]);
            grad = theList5.toArray(new String[0]);
            restoran = theList6.toArray(new String[0]);
            event = theList7.toArray(new String[0]);
            OfflineListLogic adapter1 = new OfflineListLogic(OfflineMode.this, imeBenda, ime, grad, restoran,event);
            listView.setAdapter(adapter1);
        }
        if (theList2.isEmpty()) {
            listView2.setVisibility(View.GONE);

        } else {
            HashSet<String> hashSet = new HashSet<String>();
            hashSet.addAll(theList2);
            theList2.clear();

            theList2.addAll(hashSet);

            theList2.removeAll(theList);
            Collections.sort(theList2, new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    return s1.compareToIgnoreCase(s2);
                }
            });
            imeBenda2 = theList2.toArray(new String[0]);
            OfflineSlobodniLogic adapter = new OfflineSlobodniLogic(OfflineMode.this, imeBenda2);
            listView2.setAdapter(adapter);
        }
        ListUtils.setDynamicHeight(listView2);
        ListUtils.setDynamicHeight(listView);

    }


}

/*
    ArrayList<Integer> result = new ArrayList<>();

    // Record encountered Strings in HashSet.
    HashSet<Integer> set = new HashSet<>();

// Loop over argument list.
    for (Integer item : list) {

            // If String is not in set, add it to the list and the set.
            if (!set.contains(item)) {
            result.add(item);
            set.add(item);
            }
            }
            return result;
            }*/
/*
 sItems.performClick();
         sItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
@Override
public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
        String kurac = sItems.getSelectedItem().toString();
        //date = d + "." + m + "." + kurac + ".";
        String adate = d + "." + m + "." + kurac + ".";

        try {
        calendar.setDate(new SimpleDateFormat("dd.MM.yyyy.").parse(adate).getTime(), false, true);
        } catch (ParseException e) {
        e.printStackTrace();
        }

        listView.setVisibility(View.VISIBLE);
        listView2.setVisibility(View.VISIBLE);


        ArrayList<String> theList = new ArrayList<>();
        ArrayList<String> theList2 = new ArrayList<>();
        ArrayList<String> theList3 = new ArrayList<>();
        ArrayList<String> theList4 = new ArrayList<>();
        ArrayList<String> theList5 = new ArrayList<>();
        ArrayList<String> theList6 = new ArrayList<>();
        ArrayList<String> theList7 = new ArrayList<>();

        Cursor cursor = contactBase.list_all_list();

        while (cursor.moveToNext()) {

        if (cursor.getString(2).equals(adate)) {

        theList.add(cursor.getString(1));


        theList4.add(cursor.getString(3));


        theList5.add(cursor.getString(4));


        theList6.add(cursor.getString(5));
        theList7.add(cursor.getString(6));


        theList3.add(cursor.getString(1));


                     */
/*   Collections.sort(theList, new Comparator<String>() {
                            @Override
                            public int compare(String s1, String s2) {
                                return s1.compareToIgnoreCase(s2);
                            }
                        });*//*



        } else if(!(cursor.getString(2).equals(adate) || cursor.getString(2).isEmpty() )) {

        theList2.add(cursor.getString(1));


        }



        }
        if (theList.isEmpty() && theList2.isEmpty()) {

        textView.setVisibility(View.VISIBLE);

        } else {

        textView.setVisibility(View.GONE);


        }
        if (theList.isEmpty()) {
        listView.setVisibility(View.GONE);
        } else {
        imeBenda = theList.toArray(new String[0]);
        ime = theList4.toArray(new String[0]);
        grad = theList5.toArray(new String[0]);
        restoran = theList6.toArray(new String[0]);
        event = theList7.toArray(new String[0]);
        OfflineListLogic adapter1 = new OfflineListLogic(OfflineMode.this, imeBenda, ime, grad, restoran,event);
        listView.setAdapter(adapter1);
        }
        if (theList2.isEmpty()) {
        listView2.setVisibility(View.GONE);

        } else {
        HashSet<String> hashSet = new HashSet<String>();
        hashSet.addAll(theList2);
        theList2.clear();
        //theList2.removeAll(theList);
        theList2.addAll(hashSet);
        //theList2.retainAll(theList);
        theList2.removeAll(theList);
        Collections.sort(theList2, new Comparator<String>() {
@Override
public int compare(String s1, String s2) {
        return s1.compareToIgnoreCase(s2);
        }
        });
        imeBenda2 = theList2.toArray(new String[0]);
        OfflineSlobodniLogic adapter = new OfflineSlobodniLogic(OfflineMode.this, imeBenda2);
        listView2.setAdapter(adapter);
        }
        ListUtils.setDynamicHeight(listView2);
        ListUtils.setDynamicHeight(listView);


        }

@Override
public void onNothingSelected(AdapterView<?> parentView) {


        }
        });*/

        /*sync = (Button) findViewById(R.id.button6);

            sync.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {




                }

            });*/



/*
        ArrayList<String> godine = new ArrayList<>();
        godine.add("2017");
        godine.add("2018");
        godine.add("2019");
        godine.add("2020");
        godine.add("2021");
        godine.add("2022");
        godine.add("2023");
        godine.add("2024");


        sItems = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, godine);

        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sItems.setAdapter(adapter);
        sItems.performClick();
        sItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String kurac = sItems.getSelectedItem().toString();
                //date = d + "." + m + "." + kurac + ".";



            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {


            }
        });*/

     /* calendar.setDate(Calendar.getInstance().getTimeInMillis(), false, true);
        Calendar calander = Calendar.getInstance();
        int a = calander.get(Calendar.DAY_OF_MONTH);
        int g = calander.get(Calendar.MONTH) + 1;
        int n = calander.get(Calendar.YEAR);
        d = String.valueOf(a);
        m = String.valueOf(g);
        y = String.valueOf(n);
        String datum = d + "." + m + "." + y + ".";*/


                 /*Collections.sort(theList, new Comparator<String>() {
                        @Override
                        public int compare(String s1, String s2) {
                            return s1.compareToIgnoreCase(s2);
                        }
                    });*/