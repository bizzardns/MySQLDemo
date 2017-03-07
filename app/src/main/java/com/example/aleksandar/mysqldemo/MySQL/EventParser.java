package com.example.aleksandar.mysqldemo.MySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.aleksandar.mysqldemo.Event.EventData;
import com.example.aleksandar.mysqldemo.Recycler.Adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Aleksandar on 2/16/2017.
 */

public class EventParser extends AsyncTask<Void, Void, Integer> {

    Context c;
    RecyclerView rv;
    String jsonData;

    ProgressDialog pd;
    ArrayList<EventData> events = new ArrayList<>();

    public EventParser(Context c, RecyclerView rv, String jsonData) {
        this.c = c;
        this.rv = rv;
        this.jsonData = jsonData;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(c);
        pd.setTitle("Parse");
        pd.setMessage("Parsing... please wait");
        pd.show();
    }

    @Override
    protected Integer doInBackground(Void... params) {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);
        pd.dismiss();
        if (result == 0) {
            Toast.makeText(c, "Event Parser unable to parse", Toast.LENGTH_SHORT).show();
        } else {
            //BIND DATA USING ADAPTER
            Adapter adapter = new Adapter(c, events);
            rv.setAdapter(adapter);

        }
    }

    private int parseData() {
        try {
            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo = null;
            events.clear();
            EventData ev = null;
            for (int i = 0; i < ja.length(); i++) {
                jo = ja.getJSONObject(i);
                String naziv_benda = jo.getString("naziv_benda");

                String ime = jo.getString("ime");
                String grad = jo.getString("grad");
                String lokal = jo.getString("lokal");

                ev = new EventData();
                ev.setNaziv_benda(naziv_benda);

                ev.setIme(ime);
                ev.setGrad(grad);
                ev.setLokal(lokal);

                events.add(ev);
            }
            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
