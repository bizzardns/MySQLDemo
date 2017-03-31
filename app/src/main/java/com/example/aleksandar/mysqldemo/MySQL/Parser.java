package com.example.aleksandar.mysqldemo.MySQL;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.example.aleksandar.mysqldemo.CustomListEvent;
import com.example.aleksandar.mysqldemo.Event.EventData;
import com.example.aleksandar.mysqldemo.SlobodniBendovi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Aleksandar on 2/4/2017.
 */

public class Parser extends AsyncTask<Void, Void, Integer> {

    Context c;
    String data;
    ListView lv;
    ArrayList<EventData> names = new ArrayList<>();

    public Parser(Context c, String data, ListView lv) {
        this.c = c;
        this.data = data;
        this.lv = lv;
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
            CustomListEvent adapter = new CustomListEvent(c, names);
            this.lv.setAdapter(adapter);

            SlobodniBendovi.ListUtils.setDynamicHeight(lv);
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
                String name = jo.getString("naziv_benda");
                String ime = jo.getString("ime");
                String grad = jo.getString("grad");
                String lokal = jo.getString("lokal");
                String check = jo.getString("event");


                e = new EventData();
                e.setNaziv_benda(name);
                e.setEvent(check);
                e.setIme(ime);
                e.setGrad(grad);
                e.setLokal(lokal);
                names.add(e);
            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
