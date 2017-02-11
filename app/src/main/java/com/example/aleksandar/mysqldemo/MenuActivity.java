package com.example.aleksandar.mysqldemo;

import android.content.Intent;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


    }
    public void btn(View v)
    {
        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
        startActivity(intent);
    }
    public void btn5(View v)
    {
        Intent intent = new Intent(getApplicationContext(), Main3Activity.class);
        startActivity(intent);
    }
    public void btn2(View v)
    {
        Intent intent = new Intent(getApplicationContext(), SlobodniBendovi.class);
        startActivity(intent);
    }
    public void btn1(View v)
    {
        Intent intent = new Intent(getApplicationContext(), Counter.class);
        startActivity(intent);
    }
    public void btn3(View v)
    {
        Intent intent = new Intent(getApplicationContext(), SmsActivity.class);
        startActivity(intent);
    }
    public void btn4(View v)
    {
        Intent intent = new Intent(getApplicationContext(), Register.class);
        startActivity(intent);
    }

}
