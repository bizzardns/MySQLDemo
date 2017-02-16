package com.example.aleksandar.mysqldemo.MySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

/**
 * Created by Aleksandar on 2/16/2017.
 */

public class SendRecieveEvent extends AsyncTask<Void, Void, String> {

    Context c;
    String urlEventData;
    RecyclerView rv;
    String query;
    String query2;


    public SendRecieveEvent(Context c, String urlEventData, RecyclerView rv, String query, String query2) {
        this.c = c;
        this.urlEventData = urlEventData;
        this.rv = rv;
        this.query = query;
        this.query2 = query2;

    }

    ProgressDialog pd;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(c);
        pd.setTitle("Fetch");
        pd.setMessage("Fetching... please wait");
        pd.show();
    }

    @Override
    protected String doInBackground(Void... params) {
        return this.downloadData();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        pd.dismiss();
        if (s == null) {
            Toast.makeText(c, "Neuspesno, vraca null", Toast.LENGTH_SHORT).show();
        } else {
            //CALL DATA PARSER
            EventParser parser = new EventParser(c, rv, s);
            parser.execute();
        }
    }

    private String downloadData() {

        HttpURLConnection con = Connector.connect(urlEventData);
        if (con == null) {
            return null;
        }

        try {
            OutputStream os = con.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write(new EventDataPackager(query, query2).packageData());
            bw.flush();
            os.close();
            //SOME RESPONSE
            int responseCode = con.getResponseCode();

            if (responseCode == con.HTTP_OK) {
                //RETURN SOME DATA
                InputStream is = con.getInputStream();
                //READ IT

                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer response = new StringBuffer();
                if (br != null) {
                    while ((line = br.readLine()) != null) {
                        response.append(line + "\n");
                    }
                } else {
                    return null;
                }

                return response.toString();

            } else {
                return String.valueOf(responseCode);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return null;
    }

}
