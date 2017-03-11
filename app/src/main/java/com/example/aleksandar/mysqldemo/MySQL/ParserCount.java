package com.example.aleksandar.mysqldemo.MySQL;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aleksandar.mysqldemo.Counter;
import com.example.aleksandar.mysqldemo.CustomList;
import com.example.aleksandar.mysqldemo.CustomListCount;
import com.example.aleksandar.mysqldemo.CustomListEvent;
import com.example.aleksandar.mysqldemo.Event.EventData;
import com.example.aleksandar.mysqldemo.SlobodniBendovi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Vlatko Popovic on 11-Mar-17.
 */


public class ParserCount extends AsyncTask<Void, Void, Integer> {

    Context c;
    String data;
    ListView lv;

    ArrayList<EventData> names = new ArrayList<>();


    public ParserCount(Context c, String data, ListView lv) {
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
            CustomListCount adapter = new CustomListCount(c,names);
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
                String count = jo.getString("count");



                e = new EventData();
                e.setNaziv_benda(name);
                e.setCount(count);

                names.add(e);
            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

}