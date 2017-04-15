package com.example.aleksandar.mysqldemo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aleksandar.mysqldemo.PROBA.NumberDatabse;

import java.util.ArrayList;

public class SmsNums extends AppCompatActivity {
    EditText ime, broj_telefona;
    NumberDatabse numberDatabse;
    ListView brisanje;
    ArrayList<String> theList2;
    Cursor cursor;
    ArrayList<String> theList;
    ArrayAdapter<String> adapter;
    ListAdapter listAdapter;
    String[] MobNumber;
    ListAdapter listAdapter2;
    String checkedItems;
    Button delete;
    int idx;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_nums);
        numberDatabse = new NumberDatabse(this);
        setTitle("");
        broj_telefona = (EditText) findViewById(R.id.broj_telefona);

        ime = (EditText) findViewById(R.id.ime);
       // delete = (Button) findViewById(R.id.button5);

        brisanje = (ListView) findViewById(R.id.brisanje);
        brisanje.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        theList = new ArrayList<>();
        cursor = numberDatabse.list_all_list();
        theList2 = new ArrayList<>();
        while (cursor.moveToNext()) {

            theList.add(cursor.getString(2));

        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, theList);
        //listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, theList);
        brisanje.setAdapter(adapter);

        SmsNums.ListUtils.setDynamicHeight(brisanje);

        brisanje.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                checkedItems = displayCheckedItems(brisanje
                        .getCheckedItemPositions());
                String izabrani = checkedItems;
        /*        Toast.makeText(getApplicationContext(), checkedItems,
                        Toast.LENGTH_SHORT).show();*/

                MobNumber = izabrani.split("\\s*,\\s*");
            }

        });

      /*  delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray checkedItemPositions = brisanje.getCheckedItemPositions();
                for (int i = 0; i < brisanje.getCount(); i++){

                    if (checkedItemPositions.get(i)==true)
                    {
                        numberDatabse.deleteContact(i);
                        Intent intent = new Intent(SmsNums.this, SmsActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Kontakt je obrisan!",
                                Toast.LENGTH_SHORT).show();

                    }
                    adapter.notifyDataSetChanged();

                }
                brisanje.clearChoices();
            }






                *//*int itemCount = brisanje.getCount();

                for (int i = 0; i < itemCount; i++) {
                    if (checkedItemPositions.get(i)) {
                        adapter.remove(theList.get(i));
                        --i;
                    }
                }
                checkedItemPositions.clear();
                adapter.notifyDataSetChanged();*//*

        });*/
    }

    public void Save(View v) {

        String ime1 = ime.getText().toString();
        String broj1 = broj_telefona.getText().toString();

        numberDatabse.save_u_imenik(ime1, broj1);
        Intent intent = new Intent(SmsNums.this, SmsNums.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Kontakt je sačuvan!",
                Toast.LENGTH_SHORT).show();
    }


    private String displayCheckedItems(SparseBooleanArray checkedItems) {
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < checkedItems.size(); i++) {


            if (checkedItems.valueAt(i)) {
                idx = checkedItems.keyAt(i);

                if (sb.length() > 0)
                    sb.append(", ");
                cursor.moveToPosition(idx);
                id = cursor.getInt(0);

                String broj2 = cursor.getString(1);

                //String s = (String) brojevi.getAdapter().getItem(idx);
                sb.append(broj2);

            }
        }

        return sb.toString();
    }





    public void Show(View v) {


       numberDatabse.deleteContact(id);
        Intent intent = new Intent(SmsNums.this, SmsNums.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Kontakt je obrisan!",
                Toast.LENGTH_SHORT).show();


    }

    public static class ListUtils {
        public static void setDynamicHeight(ListView mListView) {
            ListAdapter mListAdapter = mListView.getAdapter();
            if (mListAdapter == null) {
                // when adapter is null
                return;
            }
            int height = 0;
            int desiredWidth = View.MeasureSpec.makeMeasureSpec(mListView.getWidth(), View.MeasureSpec.UNSPECIFIED);
            for (int i = 0; i < mListAdapter.getCount(); i++) {
                View listItem = mListAdapter.getView(i, null, mListView);
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                height += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = mListView.getLayoutParams();
            params.height = height + (mListView.getDividerHeight() * (mListAdapter.getCount() - 1));
            mListView.setLayoutParams(params);
            mListView.requestLayout();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent inMain = new Intent(SmsNums.this, MenuActivity.class);
        inMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(inMain);
    }




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

