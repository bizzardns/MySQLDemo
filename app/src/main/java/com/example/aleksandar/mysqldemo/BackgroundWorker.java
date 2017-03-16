package com.example.aleksandar.mysqldemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.telephony.SmsManager;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Aleksandar on 1/25/2017.
 */

public class BackgroundWorker extends AsyncTask<String, Void, String> {


    Context context;
    AlertDialog alertDialog;

    static final int TIME_OUT = 2000;
    static final int MSG_DISMISS_DIALOG = 0;



    BackgroundWorker(Context ctx) {
        context = ctx;
    }

    //comment
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://macakmisamuzika.com/android/login.php";
        String addBand_url = "http://macakmisamuzika.com/android/addBand.php";
        String deleteBand_url = "http://macakmisamuzika.com/android/deleteBand.php";
        String register_url = "http://macakmisamuzika.com/android/register.php";
        String reserve_url = "http://macakmisamuzika.com/android/reserve.php";
        String delete_url = "http://macakmisamuzika.com/android/delete.php";
        String freebands_url = "http://macakmisamuzika.com/android/freebands.php";
        String reserveIzKalendara_url = "http://macakmisamuzika.com/android/reserveIzKalendara.php";

        if (type.equals("login")) {
            try {
                String user_name = params[1];
                String password = params[2];

                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (type.equals("addBand")) {
            try {
                String naziv_benda = params[1];
                String broj_telefona = params[2];


                URL url = new URL(addBand_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("naziv_benda", "UTF-8") + "=" + URLEncoder.encode(naziv_benda, "UTF-8") + "&"
                        + URLEncoder.encode("broj_telefona", "UTF-8") + "=" + URLEncoder.encode(broj_telefona, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (type.equals("register")) {
            try {
                String name = params[1];
                String surname = params[2];
                String username = params[3];
                String password = params[4];

                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("ime", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&"
                        + URLEncoder.encode("prezime", "UTF-8") + "=" + URLEncoder.encode(surname, "UTF-8") + "&"
                        + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (type.equals("rezervisi")) {
            try {
                String naziv_benda = params[1];
                String datum = params[2];
                String ime = params[3];
                String grad = params[4];
                String lokal = params[5];


                URL url = new URL(reserve_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("naziv_benda", "UTF-8") + "=" + URLEncoder.encode(naziv_benda, "UTF-8") + "&"
                        + URLEncoder.encode("datum", "UTF-8") + "=" + URLEncoder.encode(datum, "UTF-8") + "&"
                        + URLEncoder.encode("ime", "UTF-8") + "=" + URLEncoder.encode(ime, "UTF-8") + "&"
                        + URLEncoder.encode("grad", "UTF-8") + "=" + URLEncoder.encode(grad, "UTF-8") + "&"
                        + URLEncoder.encode("lokal", "UTF-8") + "=" + URLEncoder.encode(lokal, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (type.equals("izKalendara")) {
            try {
                String naziv_benda = params[1];
                String datum = params[2];
                String ime = params[3];
                String grad = params[4];
                String lokal = params[5];


                URL url = new URL(reserveIzKalendara_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("naziv_benda", "UTF-8") + "=" + URLEncoder.encode(naziv_benda, "UTF-8") + "&"
                        + URLEncoder.encode("datum", "UTF-8") + "=" + URLEncoder.encode(datum, "UTF-8") + "&"
                        + URLEncoder.encode("ime", "UTF-8") + "=" + URLEncoder.encode(ime, "UTF-8") + "&"
                        + URLEncoder.encode("grad", "UTF-8") + "=" + URLEncoder.encode(grad, "UTF-8") + "&"
                        + URLEncoder.encode("lokal", "UTF-8") + "=" + URLEncoder.encode(lokal, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        if (type.equals("obrisi")) {
            try {
                String naziv_benda = params[1];
                String datum = params[2];


                URL url = new URL(delete_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("naziv_benda", "UTF-8") + "=" + URLEncoder.encode(naziv_benda, "UTF-8") + "&"
                        + URLEncoder.encode("datum", "UTF-8") + "=" + URLEncoder.encode(datum, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } if (type.equals("deleteBand")) {
            try {
                String naziv_benda = params[1];

                URL url = new URL(deleteBand_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("naziv_benda", "UTF-8") + "=" + URLEncoder.encode(naziv_benda, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (type.equals("datum")) {
            try {
                //String urlEventData = "http://lp-developers.com/eventData.php";
                String datum = params[1];

                URL url = new URL(freebands_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("datum", "UTF-8") + "=" + URLEncoder.encode(datum, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        return null;
    }


    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();


    }

    @Override
    protected void onPostExecute(String result) {
        if (result.contains("trazeni")) {

            Toast.makeText(context,"Rezervacija jec vec uneta za trazeni bend i datum",Toast.LENGTH_SHORT).show();

        } else if (result.contains("Uspesno")) {


            Toast.makeText(context,"Uspesno dodata rezervacija",Toast.LENGTH_SHORT).show();


            final String[] MobNumber = {"0691050988"};
               String imeBenda = Main2Activity.jedan;
               String datum = Main2Activity.dva;
               String str_ime = Main2Activity.tri;
               String str_restoran = Main2Activity.cetiri;
               String str_mesto = Main2Activity.pet;



                for (int i = 0; i < MobNumber.length; i++) {
                    String tempMobileNumber = MobNumber[i];
                    sendSMS(tempMobileNumber, imeBenda + ": " + "\n" + datum + "\n" + str_ime + "\n" + str_restoran + "\n" + str_mesto + "\n");
                }
                //Toast.makeText(Main2Activity.this,"Uspesno dodata rezervacija",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, SlobodniBendovi.class);
                context.startActivity(intent);
        }
        else if (result.contains("Success")) {
            Toast.makeText(context,"Uspesno dodata rezervacija",Toast.LENGTH_SHORT).show();
            final String[] MobNumber = {"0691050988"};
            String imeBenda = UpisIzKalendara.jedan;
            String datum = UpisIzKalendara.dva;
            String str_ime = UpisIzKalendara.tri;
            String str_restoran = UpisIzKalendara.cetiri;
            String str_mesto = UpisIzKalendara.pet;

            for (int i = 0; i < MobNumber.length; i++) {
                String tempMobileNumber = MobNumber[i];
                sendSMS(tempMobileNumber, imeBenda + ": " + "\n" + datum + "\n" + str_ime + "\n" + str_restoran + "\n" + str_mesto + "\n");
            }
            //Toast.makeText(Main2Activity.this,"Uspesno dodata rezervacija",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, SlobodniBendovi.class);
            context.startActivity(intent);

        }

        else if (result.contains("obrisana")) {

            Toast.makeText(context,"Rezervacija je uspesno obrisana",Toast.LENGTH_SHORT).show();
            final String[] MobNumber = {"0691050988"};
            String imeBenda1 = CustomListEvent.jedan;
            String datum1= CustomListEvent.dva;

            for (int i = 0; i < MobNumber.length; i++) {
                String tempMobileNumber = MobNumber[i];
                sendSMS(tempMobileNumber, "Otkazana rezervacija:"+ "\n" + imeBenda1 + "\n" + datum1);
            }

        }else {
            alertDialog.setMessage(result);
            alertDialog.show();
        }
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


    public void sendSMS(String phoneNo, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            /*Toast.makeText(getApplicationContext(), "Sending...",
                    Toast.LENGTH_LONG).show();*/
           /* Toast.makeText(getApplicationContext(), "Message Sent",
                    Toast.LENGTH_LONG).show();*/
        } catch (Exception ex) {
            /*Toast.makeText(getApplicationContext(), ex.getMessage(),
                    Toast.LENGTH_LONG).show();*/
            ex.printStackTrace();
        }
    }
}
