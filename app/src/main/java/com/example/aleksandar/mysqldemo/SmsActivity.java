package com.example.aleksandar.mysqldemo;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
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
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aleksandar.mysqldemo.PROBA.NumberDatabse;
import com.wdullaer.swipeactionadapter.SwipeActionAdapter;
import com.wdullaer.swipeactionadapter.SwipeDirection;

import java.util.ArrayList;

public class SmsActivity extends AppCompatActivity implements
        SwipeActionAdapter.SwipeActionListener {


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
    protected SwipeActionAdapter mAdapter;


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
      /* numberDatabse.save_u_imenik("Sale", "123");
        numberDatabse.save_u_imenik("Mirko", "234");
        numberDatabse.save_u_imenik("Slavko", "345");*/
        brojevi = (ListView) findViewById(R.id.brojevi);
        brojevi.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        theList = new ArrayList<>();
        cursor = numberDatabse.list_all_list();
        //theList2 = new ArrayList<>();
        while (cursor.moveToNext()) {

            theList.add(cursor.getString(2));

        }


        // String[] content = new String[1000];
        for (int i=0;i<theList.size();i++) {
            theList.toArray()[i] = "Row " + (i + 1);
        }
        brojevi.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                bla = position;
                checkedItems = displayCheckedItems(brojevi.getCheckedItemPositions());

                String izabrani = checkedItems;


                MobNumber = izabrani.split("\\s*,\\s*");

                Toast.makeText(getApplicationContext(), izabrani,
                        Toast.LENGTH_SHORT).show();
            }

        });
     /*   ArrayAdapter stringAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                R.layout.row_bg,
                R.id.text,

                new ArrayList<>(lista)
        );*/
        ArrayAdapter<String> stringAdapter = new ArrayAdapter<String>
                (SmsActivity.this,
                        android.R.layout.simple_list_item_multiple_choice,
                        android.R.id.text1, theList );

        mAdapter = new SwipeActionAdapter(stringAdapter);
        mAdapter.setSwipeActionListener(this)
                .setDimBackgrounds(true)
                .setListView(brojevi);
        brojevi.setAdapter(mAdapter);

        mAdapter.addBackground(SwipeDirection.DIRECTION_FAR_LEFT,R.layout.row_bg_left_far)
                .addBackground(SwipeDirection.DIRECTION_NORMAL_LEFT, R.layout.row_bg_left)
                .addBackground(SwipeDirection.DIRECTION_FAR_RIGHT, R.layout.row_bg_right_far)
                .addBackground(SwipeDirection.DIRECTION_NORMAL_RIGHT,R.layout.row_bg_right);

        setTitle("");
        sms= (EditText) findViewById(R.id.messageText);
        final TextWatcher mTextEditorWatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() <= 153){

                    setTitle(String.valueOf(s.length())+ "/1");

                }else if (s.length() <= 306 && s.length() > 153 ){

                    setTitle(String.valueOf(s.length())+ "/2");

                }
                else if (s.length() <= 459 && s.length() > 306 ){

                    setTitle(String.valueOf(s.length())+ "/3");

                }
                else if (s.length() <= 612 && s.length() > 459 ){

                    setTitle(String.valueOf(s.length())+ "/4");

                }else{

                    setTitle(String.valueOf(s.length())+ "/5");

                }
                //This sets a textview to the current length

            }

            public void afterTextChanged(Editable s) {
            }
        };
        sms.addTextChangedListener(mTextEditorWatcher);


        send = (ImageView) findViewById(R.id.imageSend);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (brojevi.getCheckedItemCount() == 0) {
                    Toast.makeText(getApplicationContext(), "Izaberite kontakt!",
                            Toast.LENGTH_SHORT).show();


                }else if (sms.getText().toString().isEmpty()){

                    Toast.makeText(getApplicationContext(), "Unesite poruku!",
                            Toast.LENGTH_SHORT).show();

                }
                else{
                    String text = sms.getText().toString();
                    for (i = 0; i < MobNumber.length; i++) {
                        String tempMobileNumber = MobNumber[i];
                        MultipleSMS(tempMobileNumber, text);
                        /*Toast.makeText(getApplicationContext(), "Sending...",
                                Toast.LENGTH_SHORT).show();*/
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

   /* public void sendSMS(String phoneNo, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
           *//* Toast.makeText(getApplicationContext(), "Sending...",
                    Toast.LENGTH_LONG).show();*//*
           *//* Toast.makeText(getApplicationContext(), "Message Sent",
                    Toast.LENGTH_LONG).show();*//*
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),ex.getMessage(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }*/
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
        if (id == R.id.all) {

            if(brojevi.getCheckedItemCount() == theList.size()){

                for(int i = 0; i< theList.size();i++){


                    brojevi.setItemChecked(i,false);
                    checkedItems = displayCheckedItems(brojevi
                            .getCheckedItemPositions());
                    String izabrani = checkedItems;


                    MobNumber = izabrani.split("\\s*,\\s*");
                    /*Toast.makeText(getApplicationContext(), izabrani,
                            Toast.LENGTH_SHORT).show();*/


                }



            }else{

                for(int i = 0; i< theList.size();i++){


                    brojevi.setItemChecked(i,true);
                    checkedItems = displayCheckedItems(brojevi
                            .getCheckedItemPositions());
                    String izabrani = checkedItems;


                    MobNumber = izabrani.split("\\s*,\\s*");
                   /* Toast.makeText(getApplicationContext(), izabrani,
                            Toast.LENGTH_SHORT).show();*/


                }




            }



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





    }private void MultipleSMS(String phoneNumber, String message) {
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";

        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(
                SENT), 0);

        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
                new Intent(DELIVERED), 0);

        // ---when the SMS has been sent---
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        ContentValues values = new ContentValues();
                        for (int i = 0; i < MobNumber.length; i++) {
                            values.put("address", MobNumber[i]);
                            // txtPhoneNo.getText().toString());
                            values.put("body", sms.getText().toString());
                        }
                        getContentResolver().insert(
                                Uri.parse("content://sms/sent"), values);
                       /* Toast.makeText(getBaseContext(), "SMS sent",
                                Toast.LENGTH_SHORT).show();*/
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        /*Toast.makeText(getBaseContext(), "Generic failure",
                                Toast.LENGTH_SHORT).show();*/
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        /*Toast.makeText(getBaseContext(), "No service",
                                Toast.LENGTH_SHORT).show();*/
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        /*Toast.makeText(getBaseContext(), "Null PDU",
                                Toast.LENGTH_SHORT).show();*/
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                       /* Toast.makeText(getBaseContext(), "Radio off",
                                Toast.LENGTH_SHORT).show();*/
                        break;
                }
            }
        }, new IntentFilter(SENT));

        // ---when the SMS has been delivered---
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        /*Toast.makeText(getBaseContext(), "SMS delivered",
                                Toast.LENGTH_SHORT).show();*/
                        break;
                    case Activity.RESULT_CANCELED:
                        /*Toast.makeText(getBaseContext(), "SMS not delivered",
                                Toast.LENGTH_SHORT).show();*/
                        break;
                }
            }
        }, new IntentFilter(DELIVERED));

        SmsManager sms = SmsManager.getDefault();
        ArrayList<String> parts =sms.divideMessage(message);
        sms.sendMultipartTextMessage(phoneNumber, null, parts, null, null);
       // setTitle(sd);
    }
    @Override
    public boolean hasActions(int position, SwipeDirection direction){
        if(direction.isLeft()) return true;
        if(direction.isRight()) return true;
        return false;
    }

    @Override
    public boolean shouldDismiss(int position, SwipeDirection direction){
        return direction == SwipeDirection.DIRECTION_NORMAL_LEFT;
    }

    @Override
    public void onSwipe(int[] positionList, SwipeDirection[] directionList){
        for(int i=0;i<positionList.length;i++) {
            SwipeDirection direction = directionList[i];
            int position = positionList[i];
            String dir = "";


            switch (direction) {
                case DIRECTION_FAR_LEFT:
                    dir = "Far left";
                    cursor.moveToPosition(position);
                    String broj1 = cursor.getString(1);
                    Uri uri = Uri.parse("smsto:"+ broj1);
                    Intent it = new Intent(Intent.ACTION_SENDTO, uri);
                    //it.putExtra("sms_body", "");


                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.

                    }
                    startActivity(it);

                    break;
                case DIRECTION_NORMAL_LEFT:
                    dir = "Left";
                    cursor.moveToPosition(position);
                    String broj2 = cursor.getString(1);
                    Uri uri2 = Uri.parse("smsto:"+ broj2);
                    Intent it2 = new Intent(Intent.ACTION_SENDTO, uri2);
                    //it.putExtra("sms_body", "");


                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.

                    }
                    startActivity(it2);




                    break;
                case DIRECTION_FAR_RIGHT:
                    dir = "Far right";
                    cursor.moveToPosition(position);
                    String broj = cursor.getString(1);
                    String phone = "tel:" + broj;
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(phone));

                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.

                    }
                    startActivity(intent);
                    break;
                case DIRECTION_NORMAL_RIGHT:
                    /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Test Dialog").setMessage("You swiped right").create().show();*/
                    dir = "Right";
                    cursor.moveToPosition(position);
                    String broj3 = cursor.getString(1);
                    String phone3 = "tel:" + broj3;
                    Intent intent3 = new Intent(Intent.ACTION_CALL, Uri.parse(phone3));

                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.

                    }
                    startActivity(intent3);
                    break;
            }
            /*Toast.makeText(this,dir + " swipe Action triggered on " + mAdapter.getItem(position),Toast.LENGTH_SHORT).show();*/
            mAdapter.notifyDataSetChanged();
        }
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
