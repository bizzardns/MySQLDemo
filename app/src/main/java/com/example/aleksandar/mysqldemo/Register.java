package com.example.aleksandar.mysqldemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {
EditText name, surname, username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = (EditText)findViewById(R.id.et_ime);
        surname = (EditText)findViewById(R.id.et_prezime);
        username = (EditText)findViewById(R.id.et_username);
        password = (EditText)findViewById(R.id.et_password);
        setTitle("Registruj admina");
    }

    public void OnReg (View view){
        String str_name = name.getText().toString();
        String str_surname = surname.getText().toString();
        String str_username = username.getText().toString();
        String str_password = password.getText().toString();
        String type = "register";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_name, str_surname,str_username,str_password);

    }
    public boolean onCreateOptionsMenu(Menu menu){

        menu.add("Rezervisi").setIntent(new Intent(this,Main2Activity.class));
        menu.add("Obrisi rezervaciju").setIntent(new Intent(this,Main3Activity.class));
        menu.add("Pregled rezervacija").setIntent(new Intent(this, SpisakBendova.class));
        menu.add("Broj svadbi").setIntent(new Intent(this, Counter.class));
        menu.add("Posalji obavestenje").setIntent(new Intent(this, SmsActivity.class));

        return true;
    }
    public void log(View v){

        ContactDB contactBase = new ContactDB(this, null, 1);
        contactBase.delete();
    }
}
