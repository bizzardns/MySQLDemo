package com.example.aleksandar.mysqldemo;

import android.app.ProgressDialog;
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
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.aleksandar.mysqldemo.PROBA.DialogList;

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


       /* ImageView splash = (ImageView) findViewById(R.id.imageView9);
        Animation animSlide = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide);


// Start the animation like this
        splash.startAnimation(animSlide);*/



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
    {
        Toast.makeText(getApplicationContext(), "Online kalendar",
                Toast.LENGTH_SHORT).show();

        if(isNetworkConnectionAvailable()== true){
        v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.image_click));
        Intent intent = new Intent(getApplicationContext(), SlobodniBendovi.class);
        startActivity(intent);
    }}
    public void btn5(View v)
    {v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.image_click));
        if(isNetworkConnectionAvailable()== true){

        final ProgressDialog progressDialog = new ProgressDialog(MenuActivity.this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1000);
                    Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                    startActivity(intent);

                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        Toast.makeText(getApplicationContext(), "Upis rezervacija",
                Toast.LENGTH_SHORT).show();



        }
    }
    public void btn2(View v)
    {  v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.image_click));
        Toast.makeText(getApplicationContext(), "Statistika",
                Toast.LENGTH_SHORT).show();


       if(isNetworkConnectionAvailable()== true){

           Intent intent = new Intent(getApplicationContext(), Counter.class);
           startActivity(intent);
    }

    }
    public void btn1(View v)

    {
        Toast.makeText(getApplicationContext(), "Obaveštenja",
                Toast.LENGTH_SHORT).show();

        v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.image_click));
        Intent intent = new Intent(getApplicationContext(), SmsActivity.class);
        startActivity(intent);
    }
    public void btn3(View v)
    {

        v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.image_click));


        final ProgressDialog progressDialog = new ProgressDialog(MenuActivity.this);
        progressDialog.setMessage("Getting Data... Please Wait");
        progressDialog.show();

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(2000);

                    Intent intent = new Intent(getApplicationContext(), OfflineMode.class);
                    startActivity(intent);
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        Toast.makeText(getApplicationContext(), "Offline Calendar",
                Toast.LENGTH_SHORT).show();

    }
    public void btn4(View v)
    {  if(isNetworkConnectionAvailable()== true){
        v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.image_click));


        final ProgressDialog progressDialog = new ProgressDialog(MenuActivity.this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1000);

                    Intent intent = new Intent(getApplicationContext(), Register.class);
                    startActivity(intent);
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        Toast.makeText(getApplicationContext(), "Admin panel",
                Toast.LENGTH_SHORT).show();

        }
}
}

        /*RotateAnimation anim = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.ABSOLUTE);
        anim.setDuration(700);

// Start animating the image
        final ImageView splash = (ImageView) findViewById(R.id.imageView9);
        splash.startAnimation(anim);*/
        /*AlphaAnimation blinkanimation= new AlphaAnimation(0, 1); // Change alpha from fully visible to invisible
        blinkanimation.setDuration(2000); // duration - half a second
        blinkanimation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        //blinkanimation.setRepeatCount(1); // Repeat animation infinitely

        blinkanimation.setRepeatMode(Animation.REVERSE);
        ImageView splash = (ImageView) findViewById(R.id.imageView9);
        splash.startAnimation(blinkanimation);*/
// Later.. stop the animation
// splash.setAnimation(null);
       /* ImageView splash = (ImageView) findViewById(R.id.imageView9);
        ScaleAnimation zoom = new ScaleAnimation(0, 0, 1, 1);

        splash.startAnimation(zoom);*/