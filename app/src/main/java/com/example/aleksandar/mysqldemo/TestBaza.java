package com.example.aleksandar.mysqldemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        list.getData();
        datumList.getData();
        eventList.getData();
        imeList.getData();
        gradList.getData();
        restoranList.getData();


        sync = (Button) findViewById(R.id.button6);
        sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


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



                Toast.makeText(getApplicationContext(), "Baza je sinhronizovana!", Toast.LENGTH_SHORT).show();




            }
        });



    }
    public void delete(View v){

        contactBase.delete();
    }
}
