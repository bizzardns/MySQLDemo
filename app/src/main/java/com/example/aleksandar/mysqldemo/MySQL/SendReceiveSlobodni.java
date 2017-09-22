package com.example.aleksandar.mysqldemo.MySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

/**
 * Created by Aleksandar on 3/1/2017.
 */

public class SendReceiveSlobodni extends AsyncTask<Void, Void, String> {

    Context c;
    String urlAdress;
    String query;
    ListView lv;


    ProgressDialog pd;


    public SendReceiveSlobodni(String urlAdress, Context c, String query, ListView lv) {
        this.urlAdress = urlAdress;
        this.c = c;
        this.query = query;
        this.lv = lv;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(c);
        pd.setTitle("Refresh");
        pd.setMessage("Refreshing.... please wait");
        pd.show();
    }

    @Override
    protected String doInBackground(Void... params) {
        return this.sendAndReceive();
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        pd.dismiss();

        //SET LV TO EMPTY
        lv.setAdapter(null);


        if (s != null) {
            if (!s.contains("null")) {
                ParserSlobodni p = new ParserSlobodni(c, s, lv);
                p.execute();
            } else {
                lv.setVisibility(View.GONE);

                //Toast.makeText(c, "Slobodni parser null", Toast.LENGTH_SHORT).show();
            }
        } else if (s == null) {
            Toast.makeText(c, "Server not responding!", Toast.LENGTH_SHORT).show();
        }

    }


    private String sendAndReceive() {

        HttpURLConnection con = Connector.connect(urlAdress);
        if (con == null) {
            return null;
        }
        try {

            OutputStream os = new BufferedOutputStream(con.getOutputStream());

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            bw.write(new DataPackager(query).packageData());

            bw.flush();
            //RELEASE RES
            bw.close();
            os.close();
            //SOME RESPONSE
            int responseCode = con.getResponseCode();


            //DECODE
            if (responseCode == HttpURLConnection.HTTP_OK) {
                //RETURN SOME DATA
                InputStream is = con.getInputStream();


                //READ IT
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                String line = null;

                StringBuffer response = new StringBuffer();


                if (br != null) {
                    while ((line = br.readLine()) != null) {
                        response.append(line + "\n");
                    }
                    br.close();
                } else {

                    return null;
                }

                return response.toString();
            } else if (responseCode != con.HTTP_OK) {

                Toast.makeText(c, "Poruka", Toast.LENGTH_SHORT).show();


                return String.valueOf(responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
