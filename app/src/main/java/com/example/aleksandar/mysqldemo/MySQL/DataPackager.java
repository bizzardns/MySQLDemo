package com.example.aleksandar.mysqldemo.MySQL;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

/**
 * Created by Aleksandar on 2/4/2017.
 */

public class DataPackager {
    String query;

    public DataPackager(String query) {
        this.query = query;
    }

    public String packageData(){

        JSONObject jo = new JSONObject();
        StringBuffer queryString = new StringBuffer();
        try{

            jo.put("datum",query);
            Boolean firstValue = true;
            Iterator it = jo.keys();
            do {
                String key = it.next().toString();
                String value = jo.get(key).toString();
                if(firstValue){
                    firstValue = false;
                }else{
                    queryString.append("&");
                }
                queryString.append(URLEncoder.encode(key,"UTF-8"));
                queryString.append("=");
                queryString.append(URLEncoder.encode(value,"UTF-8"));


            }while (it.hasNext());
            return queryString.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
