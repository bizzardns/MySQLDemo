package com.example.aleksandar.mysqldemo.MySQL;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aleksandar.mysqldemo.CustomListCount;
import com.example.aleksandar.mysqldemo.Event.EventData;
import com.example.aleksandar.mysqldemo.R;
import com.example.aleksandar.mysqldemo.SlobodniBendovi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Aleksandar on 3/12/2017.
 */

public class ParserCountPerYear extends AsyncTask<Void, Void, Integer> {

    Context c;
    String data;
    TextView tv;
    String kurac;

    ArrayList<EventData> names = new ArrayList<>();


    public ParserCountPerYear(Context c, String data, TextView tv) {
        this.c = c;
        this.data = data;
        this.tv = tv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Integer doInBackground(Void... params) {
        return this.parse();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        if (integer == 1) {
            //BIND TO LIST VIEW
           // tv.setText(names[0]);
            //lv.setAdapter(null);
            tv.setText(kurac);
            //tv.getText();

        } else {
            Toast.makeText(c, "Unable to parse", Toast.LENGTH_SHORT).show();
        }
    }

    private int parse() {
        try {
            JSONArray ja = new JSONArray(data);
            JSONObject jo = null;
            EventData e = null;
            names.clear();
            for (int i = 0; i < ja.length(); i++) {
                jo = ja.getJSONObject(i);
                //String name = jo.getString("naziv_benda");
                String count = jo.getString("count(*)");
                kurac = count;
               // tv.setText(count);


                //e = new EventData();
                //e.setNaziv_benda(name);
               // e.setCount(count);

                //names.add(e);
            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
