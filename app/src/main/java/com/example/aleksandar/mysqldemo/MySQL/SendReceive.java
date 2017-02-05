package com.example.aleksandar.mysqldemo.MySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
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
 * Created by Aleksandar on 2/4/2017.
 */

public class SendReceive extends AsyncTask<Void, Void, String> {

    Context c;
    String urlAdress;
    String query;
    ListView lv;


    ProgressDialog pd;


    public SendReceive(String urlAdress, Context c, String query, ListView lv) {
        this.urlAdress = urlAdress;
        this.c = c;
        this.query = query;
        this.lv = lv;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(c);
        pd.setTitle("Search");
        pd.setMessage("Searching.... please wait");
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

        if (s != null){
            if (!s.contains("null")){
                Parser p =new Parser(c,s,lv);
                p.execute();
            }else {
                Toast.makeText(c,"Svi bendovi su zauzeti za traženi termin!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(c,"Svi bendovi su zauzeti za traženi termin!", Toast.LENGTH_SHORT).show();
        }

    }

    private String sendAndReceive(){

        HttpURLConnection con = Connector.connect(urlAdress);
        if(con == null){
            return null;
        }try{
            OutputStream os = con.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write(new DataPackager(query).packageData());
            bw.flush();
            //RELEASE RES
            bw.close();
            os.close();
            //SOME RESPONSE
            int responseCode = con.getResponseCode();
            //DECODE
            if (responseCode==con.HTTP_OK){
                    //RETURN SOME DATA
                    InputStream is = con.getInputStream();

                //READ IT
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String line;
                    StringBuffer response = new StringBuffer();
                    if (br != null){
                        while((line = br.readLine()) != null){
                            response.append(line+"\n");
                        }
                    }else {
                    return null;
                }
                return response.toString();
            }else{
                return String.valueOf(responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
