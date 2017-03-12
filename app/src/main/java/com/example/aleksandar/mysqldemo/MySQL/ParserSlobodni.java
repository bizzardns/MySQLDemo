package com.example.aleksandar.mysqldemo.MySQL;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aleksandar.mysqldemo.CustomListSlobodni;
import com.example.aleksandar.mysqldemo.Event.EventData;
import com.example.aleksandar.mysqldemo.SlobodniBendovi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Aleksandar on 3/1/2017.
 */

public class ParserSlobodni extends AsyncTask<Void, Void, Integer> {
    Context c;
    String data;
    ListView ls;
    ArrayAdapter adapter;
    ArrayList<EventData> names = new ArrayList<>();


    public ParserSlobodni(Context c, String data, ListView ls) {
        this.c = c;
        this.data = data;
        this.ls = ls;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Integer doInBackground(Void... params) {
        return this.parse2();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        if (integer == 1) {
            //BIND TO LIST VIEW
            CustomListSlobodni adapter = new CustomListSlobodni(c, names);
            ls.setAdapter(adapter);
            SlobodniBendovi.ListUtils.setDynamicHeight(ls);

        } else {
            Toast.makeText(c, "Unable to parse", Toast.LENGTH_SHORT).show();
        }
    }

    private int parse2() {
        try {
            JSONArray ja = new JSONArray(data);
            JSONObject jo = null;
            EventData e = null;
            names.clear();

            for (int i = 0; i < ja.length(); i++) {
                jo = ja.getJSONObject(i);
                String name = jo.getString("naziv_benda");
                e = new EventData();
                e.setNaziv_benda(name);
                names.add(e);
            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
