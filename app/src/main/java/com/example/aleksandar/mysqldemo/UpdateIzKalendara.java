package com.example.aleksandar.mysqldemo;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateIzKalendara extends AppCompatActivity {

    String bend;
    String izabraniDatum;
    EditText ime, mesto, restoran;
    TextView t1;
    EditText t2;
    String date,dan,mesec;
    //String ime_mladenaca,lokal,grad;
    public static String jedan = null;
    public static String dva = null;
    public static String tri = null;
    public static String cetiri = null;
    public static String pet = null;
    public static String dan_custom = null;
    public static String mesec_custom = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_iz_kalendara);
        setTitle("");

        Intent intent = getIntent();
        bend = intent.getStringExtra("Ime");
        izabraniDatum = intent.getStringExtra("Datum");
        String ime_mladenaca = intent.getStringExtra("Ime2");
       String lokal = intent.getStringExtra("restoran");
       String grad = intent.getStringExtra("grad");


        ime = (EditText) findViewById(R.id.editIme);
        mesto = (EditText) findViewById(R.id.editMesto);
        restoran = (EditText) findViewById(R.id.editRestoran);
         ime.setText(ime_mladenaca,TextView.BufferType.EDITABLE);
        mesto.setText(grad,TextView.BufferType.EDITABLE);
        restoran.setText(lokal,TextView.BufferType.EDITABLE);

        t1 = (TextView) findViewById(R.id.textView3);
        t1.setText(bend);
        t2 = (EditText) findViewById(R.id.textView5);
        t2.setText(izabraniDatum,TextView.BufferType.EDITABLE);

        date = SlobodniBendovi.sharedValue;
        dan = SlobodniBendovi.dan;
        mesec = SlobodniBendovi.mesec;


    }

    public void rez(View view) {

        String imeBenda = bend;
        String datum = t2.getText().toString();
        String datum_baza = izabraniDatum;
        String str_ime = ime.getText().toString();
        String str_mesto = mesto.getText().toString();
        String str_restoran = restoran.getText().toString();
        if (str_ime.isEmpty()){
            Toast.makeText(getApplicationContext(),"Unesite ime!", Toast.LENGTH_SHORT).show();
        }else if (str_restoran.isEmpty()){
            Toast.makeText(getApplicationContext(),"Unesite restoran!", Toast.LENGTH_SHORT).show();
        }else if (str_mesto.isEmpty()){
            Toast.makeText(getApplicationContext(),"Unesite grad!", Toast.LENGTH_SHORT).show();
        }else {
            UpisIzKalendara.jedan = imeBenda;
            UpisIzKalendara.dva = datum;
            UpisIzKalendara.tri = str_ime;
            UpisIzKalendara.cetiri = str_mesto;
            UpisIzKalendara.pet = str_restoran;
            CustomListEvent.jedan = imeBenda;
            CustomListEvent.dva = datum;
            CustomListEvent.dan_custom = dan;
            CustomListEvent.mesec_custom = mesec;
            SlobodniBendovi.sharedValue = datum;
            String type = "updateIzKalendara";
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, imeBenda, datum, str_ime, str_mesto, str_restoran, datum_baza);
            //finish();
            Intent intent = new Intent(UpdateIzKalendara.this, SlobodniBendovi.class);
            UpdateIzKalendara.this.startActivity(intent);

        }



    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}