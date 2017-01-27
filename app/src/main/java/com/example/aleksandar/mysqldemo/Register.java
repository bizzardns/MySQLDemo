package com.example.aleksandar.mysqldemo;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {
EditText name, surname, username, password;
    String str_name, str_surname, str_username, str_password;
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



        menu.add("Dodaj admina").setIntent(new Intent(this,Register.class));
        menu.add("Rezervisi").setIntent(new Intent(this,Main2Activity.class));
        menu.add("Obrisi rezervaciju").setIntent(new Intent(this,Main3Activity.class));
        return true;


    }

}
