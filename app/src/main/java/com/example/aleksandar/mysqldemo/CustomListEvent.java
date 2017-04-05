package com.example.aleksandar.mysqldemo;

/**
 * Created by Aleksandar on 3/1/2017.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aleksandar.mysqldemo.Event.EventData;
import com.example.aleksandar.mysqldemo.MySQL.SendReceive;
import com.example.aleksandar.mysqldemo.MySQL.SendReceiveSlobodni;

import java.util.ArrayList;

import static android.R.attr.colorBackground;
import static android.R.attr.defaultValue;


/**
 * Created by Aleksandar on 2/28/2017.
 */


public class CustomListEvent extends BaseAdapter {

    Context c;
    ArrayList<EventData> events;
    LayoutInflater inflater;
    String long_click;
    String date;
    String checked;
    public static String dan_custom = null;
    public static String mesec_custom = null;
    public static String jedan = null;
    public static String dva = null;
    String dan;
    String mesec;
    TextView check;


    public CustomListEvent(Context c, ArrayList<EventData> events) {
        this.c = c;
        this.events = events;

        //INITIALIE
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Object getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return events.get(position).getRezervacije_id();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.liste, parent, false);

        }

        TextView nameTxt = (TextView) convertView.findViewById(R.id.bendTxt);
        TextView imeTxt = (TextView) convertView.findViewById(R.id.imeTxt);
        TextView gradTxt = (TextView) convertView.findViewById(R.id.gradTxt);
        TextView lokalTxt = (TextView) convertView.findViewById(R.id.lokalTxt);
        check = (TextView) convertView.findViewById(R.id.checkTxt);


        nameTxt.setText(events.get(position).getNaziv_benda());
        check.setText(events.get(position).getEvent());
        imeTxt.setText(events.get(position).getIme());
        gradTxt.setText(events.get(position).getGrad());
        lokalTxt.setText(events.get(position).getLokal());


        //ITEM CLICKS

        convertView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {

                long_click = events.get(position).getNaziv_benda();
                checked = events.get(position).getEvent();
                date = SlobodniBendovi.sharedValue;
                dan = SlobodniBendovi.dan;
                mesec = SlobodniBendovi.mesec;



                brisi();
                //Toast.makeText(c,events.get(position).getNaziv_benda(),Toast.LENGTH_SHORT).show();
                return false;
            }


        });

        return convertView;
    }

    public void brisi() {

        if (checked.equals("")) {
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE:
                            //setTitle(izabraniDatum + " " + bend); //LOGIKA ZA BRISANJE IZ BAZE


                            String imeBenda = long_click;
                            String datum = date;
                            String type = "obrisi";
                            CustomListEvent.jedan = imeBenda;
                            CustomListEvent.dva = datum;
                            CustomListEvent.dan_custom = dan;
                            CustomListEvent.mesec_custom = mesec;
                            BackgroundWorker backgroundWorker = new BackgroundWorker(c);
                            backgroundWorker.execute(type, imeBenda, datum);

                          Intent intent = new Intent(c, SlobodniBendovi.class);
                            c.startActivity(intent);
                        /*Toast.makeText(c, "Kliknite opet na datum!", Toast.LENGTH_SHORT).show();*/

                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            // Toast.makeText(Main3Activity.this, "NO", Toast.LENGTH_SHORT).show();
                            break;


                        case DialogInterface.BUTTON_NEUTRAL:
                            //Toast.makeText(c, "OVDE IDE LOGIKA", Toast.LENGTH_SHORT).show();
                            String imeBenda1 = long_click;
                            String datum1 = date;
                            CustomListEvent.dan_custom = dan;
                            CustomListEvent.mesec_custom = mesec;

                            String type1 = "check";
                            BackgroundWorker backgroundWorker1 = new BackgroundWorker(c);
                            backgroundWorker1.execute(type1, imeBenda1, datum1);
                            Intent intent1 = new Intent(c, SlobodniBendovi.class);
                            c.startActivity(intent1);



                            break;
                    }
                }
            };

            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(c);
            builder.setMessage("Da li želite da obrišete rezervaciju?").setPositiveButton("Da", dialogClickListener)
                    .setNegativeButton("Ne", dialogClickListener).setNeutralButton("CHECK", dialogClickListener).show();


        } else if (checked.equals("✓")) {


            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE:
                            //setTitle(izabraniDatum + " " + bend); //LOGIKA ZA BRISANJE IZ BAZE


                            String imeBenda = long_click;
                            String datum = date;
                            String type = "obrisi";
                            CustomListEvent.jedan = imeBenda;
                            CustomListEvent.dva = datum;
                            CustomListEvent.dan_custom = dan;
                            CustomListEvent.mesec_custom = mesec;
                            BackgroundWorker backgroundWorker = new BackgroundWorker(c);
                            backgroundWorker.execute(type, imeBenda, datum);
                            Intent intent = new Intent(c, SlobodniBendovi.class);
                            c.startActivity(intent);
                        /*Toast.makeText(c, "Kliknite opet na datum!", Toast.LENGTH_SHORT).show();*/

                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            // Toast.makeText(Main3Activity.this, "NO", Toast.LENGTH_SHORT).show();
                            break;


                        case DialogInterface.BUTTON_NEUTRAL:
                            //Toast.makeText(c, "OVDE IDE LOGIKA", Toast.LENGTH_SHORT).show();
                            String imeBenda1 = long_click;
                            String datum1 = date;
                            CustomListEvent.dan_custom = dan;
                            CustomListEvent.mesec_custom = mesec;
                            String type1 = "unCheck";
                            BackgroundWorker backgroundWorker1 = new BackgroundWorker(c);
                            backgroundWorker1.execute(type1, imeBenda1, datum1);
                           Intent intent1 = new Intent(c, SlobodniBendovi.class);
                            c.startActivity(intent1);


                            break;
                    }
                }
            };

            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(c);
            builder.setMessage("Da li želite da obrišete rezervaciju?").setPositiveButton("Da", dialogClickListener)
                    .setNegativeButton("Ne", dialogClickListener).setNeutralButton("UNCHECK", dialogClickListener).show();


        }
    }


}
