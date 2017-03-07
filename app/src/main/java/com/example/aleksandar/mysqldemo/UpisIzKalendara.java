package com.example.aleksandar.mysqldemo;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UpisIzKalendara extends AppCompatActivity {

    String  bend;
    String izabraniDatum;
    EditText ime, event, mesto, restoran;
    TextView t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upis_iz_kalendara);
        setTitle("Rezervisi");

          Intent intent = getIntent();
           bend = intent.getStringExtra("Ime");
          izabraniDatum = intent.getStringExtra("Datum");



        ime = (EditText)findViewById(R.id.editIme);
        mesto = (EditText)findViewById(R.id.editMesto);
        restoran = (EditText)findViewById(R.id.editRestoran);

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
        String type = "rezervisi";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, imeBenda, datum,str_ime,str_mesto,str_restoran);

    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
