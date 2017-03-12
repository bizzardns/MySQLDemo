package com.example.aleksandar.mysqldemo.MySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.TextView;
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
 * Created by Aleksandar on 3/12/2017.
 */

public class SendReceiveCountPerYear extends AsyncTask<Void, Void, String> {

    Context c;
    String urlAdress;
    String query;
    TextView tv;
    ProgressDialog pd;


    public SendReceiveCountPerYear(String urlAdress, Context c, String query, TextView tv) {
        this.urlAdress = urlAdress;
        this.c = c;
        this.query = query;
        this.tv = tv;

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

        //lv.setAdapter(null);


        if (s != null){
            if (!s.contains("null")){
                ParserCountPerYear p =new ParserCountPerYear(c,s,tv);
                p.execute();

            }else {
                //lv.setVisibility(View.GONE);

            }
        }else if (s == null) {
            Toast.makeText(c, "Send receive null", Toast.LENGTH_SHORT).show();
        }

    }

    private String sendAndReceive(){

        HttpURLConnection con = Connector.connect(urlAdress);
        if(con == null){
            return null;
        }try{
            OutputStream os = con.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write(new DataPackagerCount(query).packageData());
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
