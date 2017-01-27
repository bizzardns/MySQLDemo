package com.example.aleksandar.mysqldemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.appcompat.R.styleable.AlertDialog;

public class Main3Activity extends AppCompatActivity {

    String bend;
    String izabraniDatum;

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        setTitle("Obrisi");


        List<String> spinnerArray = new ArrayList<>(); //PUNITI LISTU IZ BAZE!
        spinnerArray.add("Misa Macak");
        spinnerArray.add("Roko Maroko");
        spinnerArray.add("Bend od Marcipana");
        spinnerArray.add("La Bombonjera");
        spinnerArray.add("Tapas");
        spinnerArray.add("Venecija");
        spinnerArray.add("Mandarina");
        spinnerArray.add("Kiwi");
        spinnerArray.add("Marx");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner sItems = (Spinner) findViewById(R.id.spinner2);
        sItems.setAdapter(adapter);
        sItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                bend = sItems.getSelectedItem().toString();
                //LOGIKA ZA DOBAVLJANJE IZABRANOG BENDA!
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker2);
        Calendar today = Calendar.getInstance();



        datePicker.init(

                today.get(Calendar.YEAR),
                today.get(Calendar.MONTH) ,
                today.get(Calendar.DAY_OF_MONTH),


                new DatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        izabraniDatum = dayOfMonth + "." + "\n" + (monthOfYear + 1) + "." + "\n" + year + ".";

                        //LOGIKA ZA IZABRANI DATUM!
                    }
                });




    }
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add("Dodaj admina").setIntent(new Intent(this,Register.class));
        menu.add("Rezervisi").setIntent(new Intent(this,Main2Activity.class));
        menu.add("Broj svadbi").setIntent(new Intent(this,Main3Activity.class));

        return true;

    }
    public void rez(View v){


        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Toast.makeText(Main3Activity.this, "YES", Toast.LENGTH_SHORT).show();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                       // Toast.makeText(Main3Activity.this, "NO", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();



    }

}
