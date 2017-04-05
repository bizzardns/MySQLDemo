package com.example.aleksandar.mysqldemo;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpisIzKalendara extends AppCompatActivity {

    String bend;
    String izabraniDatum;
    EditText ime, mesto, restoran;
    TextView t1, t2;
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
        setContentView(R.layout.activity_upis_iz_kalendara);
        setTitle("");

        Intent intent = getIntent();
        bend = intent.getStringExtra("Ime");
        izabraniDatum = intent.getStringExtra("Datum");


        ime = (EditText) findViewById(R.id.editIme);
        mesto = (EditText) findViewById(R.id.editMesto);
        restoran = (EditText) findViewById(R.id.editRestoran);

        t1 = (TextView) findViewById(R.id.textView3);
        t1.setText(bend);
        t2 = (TextView) findViewById(R.id.textView5);
        t2.setText(izabraniDatum);


    }

    public void rez(View view) {

        String imeBenda = bend;
        String datum = izabraniDatum;
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
            String type = "izKalendara";
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, imeBenda, datum, str_ime, str_mesto, str_restoran);
           //finish();
           Intent intent = new Intent(UpisIzKalendara.this, SlobodniBendovi.class);
            UpisIzKalendara.this.startActivity(intent);

        }



    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
