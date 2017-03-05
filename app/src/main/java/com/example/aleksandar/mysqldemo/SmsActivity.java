package com.example.aleksandar.mysqldemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SmsActivity extends AppCompatActivity {
    EditText sms;
    FloatingActionButton send;
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
                int id = menuItem.getItemId();
                if (id == R.id.nav1) {
                    Intent myIntent = new Intent(SmsActivity.this, Main2Activity.class);
                    SmsActivity.this.startActivity(myIntent);

                }
                else if (id == R.id.nav2)
                {
                    Intent myIntent = new Intent(SmsActivity.this, Main3Activity.class);
                    SmsActivity.this.startActivity(myIntent);
                }
                else if (id == R.id.nav3)
                {
                    Intent myIntent = new Intent(SmsActivity.this, SlobodniBendovi.class);
                    SmsActivity.this.startActivity(myIntent);

                }
                else if (id == R.id.nav4)
                {
                    Intent myIntent = new Intent(SmsActivity.this, Counter.class);
                    SmsActivity.this.startActivity(myIntent);

                }
                else if (id == R.id.nav5)
                {
                    Intent myIntent = new Intent(SmsActivity.this, SmsActivity.class);
                    SmsActivity.this.startActivity(myIntent);

                }
                else if (id == R.id.nav6)
                {
                    Intent myIntent = new Intent(SmsActivity.this, Register.class);
                    SmsActivity.this.startActivity(myIntent);

                }


                return true;
            }
        } );






        setTitle("Posalji obavestenje");
        sms= (EditText) findViewById(R.id.messageText);
        final String[] MobNumber = {"0691050988"};
        send = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
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
    public void sendSMS(String phoneNo, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            Toast.makeText(getApplicationContext(), "Sending...",
                    Toast.LENGTH_LONG).show();
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

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to close this activity?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();

                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
    }


