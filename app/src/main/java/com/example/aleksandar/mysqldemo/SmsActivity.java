package com.example.aleksandar.mysqldemo;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.icu.util.Calendar;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class SmsActivity extends AppCompatActivity {



    NumberDatabse numberDatabse;
    ListView brojevi;
    ArrayList<String> theList2;
    Cursor cursor;
    ArrayList<String> theList;
    ArrayAdapter listAdapter;
    String[] MobNumber;
    String checkedItems;
    EditText editText;
    android.widget.SearchView sv;
    int idx;
    int id;
    int broj;
    int name;
int bla;





    EditText sms;
    ImageView send;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
        setContentView(R.layout.activity_sms);

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
                //menuItem.setChecked(true);
                int id = menuItem.getItemId();
                if (id == R.id.nav1) {
                    Intent myIntent = new Intent(SmsActivity.this, SlobodniBendovi.class);
                    SmsActivity.this.startActivity(myIntent);

                } else if (id == R.id.nav2) {
                    Intent myIntent = new Intent(SmsActivity.this, Main2Activity.class);
                    SmsActivity.this.startActivity(myIntent);
                } else if (id == R.id.nav3) {
                    Intent myIntent = new Intent(SmsActivity.this, Counter.class);
                    SmsActivity.this.startActivity(myIntent);

                } else if (id == R.id.nav4) {
                    Intent myIntent = new Intent(SmsActivity.this, SmsActivity.class);
                    SmsActivity.this.startActivity(myIntent);

                } else if (id == R.id.nav5) {
                    Intent myIntent = new Intent(SmsActivity.this, OfflineMode.class);
                    SmsActivity.this.startActivity(myIntent);

                } else if (id == R.id.nav6) {
                    Intent myIntent = new Intent(SmsActivity.this, Register.class);
                    SmsActivity.this.startActivity(myIntent);

                }


                return true;
            }
        } );
        // sv = (android.widget.SearchView) findViewById(R.id.searchView);
        numberDatabse = new NumberDatabse(this);
        brojevi = (ListView) findViewById(R.id.brojevi);
        brojevi.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        theList = new ArrayList<>();
        cursor = numberDatabse.list_all_list();
        theList2 = new ArrayList<>();
        while (cursor.moveToNext()) {

            theList.add(cursor.getString(2));

        }

        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, theList);
        brojevi.setAdapter(listAdapter);
        //  SmsActivity.ListUtils.setDynamicHeight(brojevi);
     /*   sv.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {

                listAdapter.getFilter().filter(text);
                return false;
            }
        });*/


        brojevi.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


                checkedItems = displayCheckedItems(brojevi
                        .getCheckedItemPositions());
                String izabrani = checkedItems;


                MobNumber = izabrani.split("\\s*,\\s*");
                Toast.makeText(getApplicationContext(), checkedItems,
                        Toast.LENGTH_SHORT).show();


            }

        });


        setTitle("");
        sms= (EditText) findViewById(R.id.messageText);
        send = (ImageView) findViewById(R.id.imageSend);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = sms.getText().toString();
                for (int i = 0; i < MobNumber.length; i++) {
                    String tempMobileNumber = MobNumber[i];
                    sendSMS(tempMobileNumber, text);
                }

            }
        });
    }
 /*   public boolean onCreateOptionsMenu(Menu menu) {


        menu.add("Settings").setIntent(new Intent(this, SmsNums.class));




        return true;

    }*/

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search, menu);
        MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listAdapter.notifyDataSetChanged();
                listAdapter.getFilter().filter(newText);


                return true;
            }

        });

        // menu.add("Add Contact").setIntent(new Intent(this, SmsNums.class));

        return true;
    }

    public void sendSMS(String phoneNo, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
           /* Toast.makeText(getApplicationContext(), "Sending...",
                    Toast.LENGTH_LONG).show();*/
            Toast.makeText(getApplicationContext(), "Message Sent",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),ex.getMessage(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){

            return true;

        }

        int id = item.getItemId();


        if (id == R.id.imenik) {



            Intent intent = new Intent(SmsActivity.this, SmsNums.class);
            startActivity(intent);



            return true;
        }


        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setIcon(R.drawable.sms)
                .setTitle("Exit")
                .setMessage("Da li ste sigurni?")
                .setPositiveButton("Da", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();

                    }

                })
                .setNegativeButton("Ne", null)
                .show();
    }


    @NonNull
    private String displayCheckedItems(SparseBooleanArray checkedItems) {
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < checkedItems.size(); i++) {

            if (checkedItems.valueAt(i)) {
                idx = checkedItems.keyAt(i);

                if (sb.length() > 0)
                    sb.append(", ");
                cursor.moveToPosition(idx);
                id = cursor.getInt(0);
                broj =cursor.getInt(1);
                name =cursor.getInt(2);
                String broj2 = cursor.getString(1);

                //String s = (String) brojevi.getAdapter().getItem(idx);
                sb.append(broj2);

            }
        }

        return sb.toString();
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

    public void delete(View v) {


        numberDatabse.deleteContact(id);





    }

}