package com.example.aleksandar.mysqldemo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aleksandar.mysqldemo.SQLiteData.GetEvent;
import com.example.aleksandar.mysqldemo.SQLiteData.GetGrad;
import com.example.aleksandar.mysqldemo.SQLiteData.GetIme;
import com.example.aleksandar.mysqldemo.SQLiteData.GetRestoran;

public class TestBaza extends AppCompatActivity {

    ReservationList list = new ReservationList();
    DatumList datumList = new DatumList();
    GetEvent eventList = new GetEvent();
    GetIme imeList = new GetIme();
    GetGrad gradList = new GetGrad();
    GetRestoran restoranList = new GetRestoran();
    ContactDB contactBase;
    TextView tv;
    String d;
    String n;
    String j;
    String h;
    String l;
    Button sync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_baza);
        contactBase = new ContactDB(this, null, 1);



        sync = (Button) findViewById(R.id.button6);
        sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                list.getData();
                datumList.getData();
                eventList.getData();
                imeList.getData();
                gradList.getData();
                restoranList.getData();


                contactBase.delete();

                for (int a = 0; a < list.data.length; a++) {

                    for (int i = 0; i < datumList.data.length; i++) {

                        d = datumList.data[a];
                    }
                    for (int i = 0; i < eventList.data.length; i++) {

                        n= eventList.data[a];
                    }
                    for (int i = 0; i < imeList.data.length; i++) {

                        j= imeList.data[a];
                    }
                    for (int i = 0; i < gradList.data.length; i++) {

                        h= gradList.data[a];
                    }
                    for (int i = 0; i < restoranList.data.length; i++) {

                        l= restoranList.data[a];
                    }


                   contactBase.addContact(list.data[a], d , n,j,h,l);



                }
               AlertDialog.Builder builder1 = new AlertDialog.Builder(TestBaza.this);
                builder1.setMessage("Baza je sinhronizovana!");
                builder1.setPositiveButton("Ok",
                       new DialogInterface.OnClickListener() {

                           @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                       });
               builder1.show();


               // Toast.makeText(getApplicationContext(), "Baza je sinhronizovana!", Toast.LENGTH_SHORT).show();



            }

        });



    }
    public void delete(View v){

        contactBase.delete();
    }
}
