package com.example.aleksandar.mysqldemo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SmsNums extends AppCompatActivity {
    EditText ime,broj_telefona;
    NumberDatabse numberDatabse;
    ListView brojevi;
    ArrayList<String> theList2;
    Cursor cursor;
    ArrayList<String> theList;
    ListAdapter listAdapter;
    String[] MobNumber;
     ListAdapter listAdapter2;
    String checkedItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_nums);
        numberDatabse = new NumberDatabse(this, null, 1);
         setTitle("");
        ime = (EditText) findViewById(R.id.ime);
        ime.clearFocus();
        broj_telefona = (EditText) findViewById(R.id.broj_telefona);
        broj_telefona.clearFocus();
       /* brojevi = (ListView) findViewById(R.id.brojevi);
        brojevi.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        theList = new ArrayList<>();
        cursor = numberDatabse.prikazi_ceo_imenik();
        theList2 = new ArrayList<>();
        while (cursor.moveToNext()) {

            theList.add(cursor.getString(2));

        }

        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, theList);
        brojevi.setAdapter(listAdapter);*/


      /*  brojevi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                cursor.moveToPosition(position);
                String broj = cursor.getString(1);
                theList2.add(broj);

                Toast.makeText(getApplicationContext(), broj, Toast.LENGTH_SHORT).show();

            }

        });*/
  /*      brojevi.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                 checkedItems = displayCheckedItems(brojevi
                        .getCheckedItemPositions());
                String izabrani = checkedItems;
                Toast.makeText(getApplicationContext(), checkedItems,
                        Toast.LENGTH_SHORT).show();

               MobNumber = izabrani.split("\\s*,\\s*");
            }

        });*/






    }

    public void Save(View v){

          String ime1 = ime.getText().toString();
          String broj1 = broj_telefona.getText().toString();

            numberDatabse.save_u_imenik(ime1,broj1);
        Intent intent = new Intent(SmsNums.this, SmsActivity.class);
        startActivity(intent);
    }
    public void Show(View v){





    }

  /*  private String displayCheckedItems(SparseBooleanArray checkedItems) {
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < checkedItems.size(); i++) {

            if (checkedItems.valueAt(i)) {
                int idx = checkedItems.keyAt(i);

                if (sb.length() > 0)
                    sb.append(", ");
                cursor.moveToPosition(idx);
                String broj = cursor.getString(1);
                String s = (String) brojevi.getAdapter().getItem(idx);
                sb.append(broj);

            }
        }

        return sb.toString();
    }*/


/*    public void sendSMS(String phoneNo, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
           *//* Toast.makeText(getApplicationContext(), "Sending...",
                    Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), "Message Sent",
                    Toast.LENGTH_LONG).show();*//*
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),ex.getMessage(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }*/


}

