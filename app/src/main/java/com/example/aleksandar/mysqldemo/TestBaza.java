package com.example.aleksandar.mysqldemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TestBaza extends AppCompatActivity {

    ReservationList list = new ReservationList();
    DatumList datumList = new DatumList();
    ContactDB contactBase;
    TextView tv;
    String d;
    String n;
    Button sync;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_baza);
        contactBase = new ContactDB(this, null, 1);

        list.getData();
        datumList.getData();

        tv =(TextView) findViewById(R.id.textView3);
        tv.invalidate();
        sync = (Button) findViewById(R.id.button6);
        sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                contactBase.delete();

                for (int a = 0; a < list.data.length; a++) {

                    for (int i = 0; i < datumList.data.length; i++) {

                        d = datumList.data[a];
                    }
                    for (int i = 0; i < datumList.data.length; i++) {

                        n= datumList.data[a];
                    }

                    contactBase.addContact(list.data[a], d , n);
                }



                contactBase.list_all_contact(tv);



            }
        });



    }
    public void delete(View v){

        contactBase.delete();
    }
}
