package com.example.aleksandar.mysqldemo.MySQL;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aleksandar.mysqldemo.Event.EventData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Aleksandar on 3/12/2017.
 */

public class ParserCountPerYear extends AsyncTask<Void, Void, Integer> {

    Context c;
    private String data;
    private TextView tv;
    private String count;

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
            //BIND TO TEXT VIEW
            tv.setText(count);
        } else {
            Toast.makeText(c, "Server not responding!", Toast.LENGTH_SHORT).show();
        }
    }

    private int parse() {
        try {
            JSONArray ja = new JSONArray(data);
            JSONObject jo;

            names.clear();
            for (int i = 0; i < ja.length(); i++) {
                jo = ja.getJSONObject(i);
                count = jo.getString("count(*)");
            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
