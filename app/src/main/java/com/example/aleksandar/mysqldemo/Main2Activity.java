package com.example.aleksandar.mysqldemo;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {


    String bend;
    String izabraniDatum;

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("Rezervisi");


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

        Button clickButton = (Button) findViewById(R.id.button);
        clickButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                setTitle(izabraniDatum + " " + bend); // RADI !!! ALI JE DATUM PREBACEN U STRING
                Toast.makeText(Main2Activity.this, "Dodato u bazu", Toast.LENGTH_SHORT).show(); //TOAST PORUKA!


            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu){

        menu.add("Obrisi rezervaciju").setIntent(new Intent(this,Main3Activity.class));
        menu.add("Broj svadbi").setIntent(new Intent(this,Main2Activity.class));
        menu.add("Dodaj admina").setIntent(new Intent(this,Register.class));


        return true;

    }
}
