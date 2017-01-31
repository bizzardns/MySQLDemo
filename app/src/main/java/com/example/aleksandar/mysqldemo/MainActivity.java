package com.example.aleksandar.mysqldemo;


import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText UserNameEt, PasswordEt;
    ContactDB contactBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Login");
        UserNameEt = (EditText) findViewById(R.id.etUserName);
        PasswordEt = (EditText) findViewById(R.id.etPassword);
        contactBase = new ContactDB(this, null, 1);
        final Cursor cursor = contactBase.list_all_contact();
         while (cursor.moveToNext()) {
            UserNameEt.setText(cursor.getString(1));
            PasswordEt.setText(cursor.getString(2));
        }
    }
    public void OnLogin(View view) {
        String username = UserNameEt.getText().toString();
        String password = PasswordEt.getText().toString();
        if (!username.isEmpty()){
            String type = "login";
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, username, password);
        }else{
            Toast.makeText(MainActivity.this, "Unesite username i password", Toast.LENGTH_LONG).show();
        }
    }
    public void itemClicked(View v) {
        final String username1 = UserNameEt.getText().toString();
        final String password2 = PasswordEt.getText().toString();
        CheckBox box = (CheckBox) v;
        if (box.isChecked()) {
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE:
                                contactBase.addContact(username1, password2);
                            break;
                        case DialogInterface.BUTTON_NEGATIVE:
                            break;
                    }
                }
            };
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();
        }
    }
}


