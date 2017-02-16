package com.example.aleksandar.mysqldemo.MySQL;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

/**
 * Created by Aleksandar on 2/16/2017.
 */

public class EventDataPackager {
    String query;
    String query2;

    public EventDataPackager(String query, String query2) {
        this.query = query;
        this.query2 = query2;
    }

    public String packageData() {
        JSONObject jo = new JSONObject();
        StringBuffer queryString = new StringBuffer();
        try {

            jo.put("naziv_benda", query);
            jo.put("datum", query2);


            Boolean firstValue = true;
            Iterator it = jo.keys();
            do {
                String key = it.next().toString();
                String value = jo.get(key).toString();
                if (firstValue) {
                    firstValue = false;
                } else {

                    queryString.append("&");
                }

                queryString.append(URLEncoder.encode(key, "UTF-8"));
                queryString.append("=");
                queryString.append(URLEncoder.encode(value, "UTF-8"));


            } while (it.hasNext());
            return queryString.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
