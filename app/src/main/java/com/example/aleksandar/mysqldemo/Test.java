package com.example.aleksandar.mysqldemo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Vlatko Popovic on 04-Feb-17.
 */

public class Test {
    String adress = "http://lp-developers.com/freebands.php";
    InputStream is = null;
    String line = null;
    String result = null;
    String[] data;


    void getData() {
        try {
            URL url = new URL(adress);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            is = new BufferedInputStream(conn.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            is.close();
            result = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            JSONArray ja = new JSONArray(result);
            JSONObject jo = null;
            data = new String[ja.length()];
            for (int i = 0; i < ja.length(); i++) {
                jo = ja.getJSONObject(i);
                data[i] = jo.getString("naziv_benda");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
