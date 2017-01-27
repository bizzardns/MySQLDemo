package com.example.aleksandar.mysqldemo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText UserNameEt, PasswordEt;
    ContactDB contactBase;


   // ListView lv;
    //ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Login");



        UserNameEt = (EditText)findViewById(R.id.etUserName);
        PasswordEt = (EditText)findViewById(R.id.etPassword);


        contactBase = new ContactDB(this, null, 1);

        final Cursor cursor = contactBase.list_all_contact();

        while (cursor.moveToNext()) {
            UserNameEt.setText(cursor.getString(1));
            PasswordEt.setText(cursor.getString(2));
        }

    }

    public void OnLogin(View view){



        String username = UserNameEt.getText().toString();
        String password = PasswordEt.getText().toString();
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, password);

    }
    public void itemClicked(View v){

        String username = UserNameEt.getText().toString();
        String password = PasswordEt.getText().toString();
        CheckBox box = (CheckBox)v;
        if(box.isChecked()){

            if (!(username.isEmpty()&&password.isEmpty())){

                contactBase.addContact(username,password);

            }

        }


    }


   public void OpenReg (View view){
    startActivity(new Intent(this, Register.class));
  }

}
