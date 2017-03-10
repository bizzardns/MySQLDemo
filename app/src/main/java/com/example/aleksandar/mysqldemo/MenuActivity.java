package com.example.aleksandar.mysqldemo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

public class MenuActivity extends AppCompatActivity {

    public static String sharedValue = null;
    public static String dan = null;
    public static String mesec = null;

    public void checkNetworkConnection(){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("No internet Connection");
        builder.setMessage("Please turn on internet connection to continue");
        builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
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
        setContentView(R.layout.activity_menu);


        Calendar calander = Calendar.getInstance();
        int a = calander.get(Calendar.DAY_OF_MONTH);
        int g = calander.get(Calendar.MONTH) + 1;
        int n = calander.get(Calendar.YEAR);
        String d = String.valueOf(a);
        String m = String.valueOf(g);
        String y = String.valueOf(n);
        String datum = d + "." + m + "." + y + ".";

        MenuActivity.sharedValue = datum;
        MenuActivity.dan = d;
        MenuActivity.mesec = m;










    }
    public void btn(View v)
    {   if(isNetworkConnectionAvailable()== true){
        v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.image_click));
        Intent intent = new Intent(getApplicationContext(), SlobodniBendovi.class);
        startActivity(intent);
    }}
    public void btn5(View v)
    {  isNetworkConnectionAvailable();
        v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.image_click));
        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
        startActivity(intent);
    }
    public void btn2(View v)
    {  if(isNetworkConnectionAvailable()== true){

        v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.image_click));
        Intent intent = new Intent(getApplicationContext(), Counter.class);
        startActivity(intent);


    }

    }
    public void btn1(View v)

    {   if(isNetworkConnectionAvailable()== true){
        v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.image_click));
        Intent intent = new Intent(getApplicationContext(), SmsActivity.class);
        startActivity(intent);
    }}
    public void btn3(View v)
    {   if(isNetworkConnectionAvailable()== true){
        v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.image_click));
        Intent intent = new Intent(getApplicationContext(), OfflineMode.class);
        startActivity(intent);
    }}
    public void btn4(View v)
    {   if(isNetworkConnectionAvailable()== true){
        v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.image_click));
        Intent intent = new Intent(getApplicationContext(), Register.class);
        startActivity(intent);
    }}

}
