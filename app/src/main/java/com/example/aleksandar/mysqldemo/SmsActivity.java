package com.example.aleksandar.mysqldemo;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.telephony.SmsManager;
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
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aleksandar.mysqldemo.PROBA.NumberDatabse;

import java.util.ArrayList;

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
    int i;
    Context c;


    EditText sms;
    ImageView send;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        setContentView(R.layout.activity_sms);

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
        });
        // sv = (android.widget.SearchView) findViewById(R.id.searchView);
        numberDatabse = new NumberDatabse(this);
        brojevi = (ListView) findViewById(R.id.brojevi);
        brojevi.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        theList = new ArrayList<>();
        cursor = numberDatabse.list_all_list();
        //theList2 = new ArrayList<>();
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
                bla = position;
                checkedItems = displayCheckedItems(brojevi
                        .getCheckedItemPositions());
                String izabrani = checkedItems;


                MobNumber = izabrani.split("\\s*,\\s*");
             /*Toast.makeText(getApplicationContext(), izabrani,
                        Toast.LENGTH_SHORT).show();*/


            }

        });
        brojevi.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                String broj = cursor.getString(1);
                String phone = "tel:" + broj;
                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse(phone));

                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return true;
                }
                startActivity(i);

                return true;
            }
        });





        setTitle("");
        sms= (EditText) findViewById(R.id.messageText);
        send = (ImageView) findViewById(R.id.imageSend);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkedItems == null) {
                    Toast.makeText(getApplicationContext(), "Izaberite kontakt!",
                            Toast.LENGTH_SHORT).show();


                }else{

                    String text = sms.getText().toString();
                    for (i = 0; i < MobNumber.length; i++) {
                        String tempMobileNumber = MobNumber[i];
                        sendSMS(tempMobileNumber, text);

                        Toast.makeText(getApplicationContext(), "Sending...",
                                Toast.LENGTH_SHORT).show();

                    }
                    if (i == MobNumber.length) {

                        Intent intent = new Intent(SmsActivity.this, SmsActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Message Sent!",
                                Toast.LENGTH_SHORT).show();

                    }


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





        return true;
    }

    public void sendSMS(String phoneNo, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
           /* Toast.makeText(getApplicationContext(), "Sending...",
                    Toast.LENGTH_LONG).show();*/
           /* Toast.makeText(getApplicationContext(), "Message Sent",
                    Toast.LENGTH_LONG).show();*/
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

                        Intent inMain=new Intent(SmsActivity.this, MenuActivity.class);
                        inMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(inMain);


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
/*  ZA CEKIRANJE SVIH ITEMA
   if (id == R.id.All) {
           int size = listAdapter.getCount();
           // boolean check = brojevi.isItemChecked(0);
           if(brojevi.isItemChecked(0)){
           for(int i = 0; i<=size; i++){
           brojevi.setItemChecked(i, false);
           }
           } else {
           for(int i = 0; i<=size; i++){
           brojevi.setItemChecked(i, true);
           }
//checkedItems = brojevi.getCheckedItemPositions().toString();
           checkedItems = displayCheckedItems(brojevi
           .getCheckedItemPositions());
           String izabrani = checkedItems;


           MobNumber = izabrani.split("\\s*,\\s*");

           Toast.makeText(getApplicationContext(), checkedItems,
           Toast.LENGTH_SHORT).show();
           }


           return true;
           }*/
