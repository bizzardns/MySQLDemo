package com.example.aleksandar.mysqldemo;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SmsActivity extends AppCompatActivity {
    EditText sms;
    FloatingActionButton send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        setTitle("Posalji obavestenje");
        sms= (EditText) findViewById(R.id.messageText);
        final String[] MobNumber = {"0691050988","0649258171"};
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
    } public boolean onCreateOptionsMenu(Menu menu){
        menu.add("Rezervisi").setIntent(new Intent(this,Main2Activity.class));
        menu.add("Obrisi rezervaciju").setIntent(new Intent(this,Main3Activity.class));
        menu.add("Broj svadbi").setIntent(new Intent(this,SmsActivity.class));
        menu.add("Dodaj admina").setIntent(new Intent(this, Register.class));
        return true;
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
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }
}
