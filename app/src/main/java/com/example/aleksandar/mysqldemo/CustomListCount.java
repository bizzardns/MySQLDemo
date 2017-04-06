package com.example.aleksandar.mysqldemo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aleksandar.mysqldemo.Event.EventData;

import java.util.ArrayList;

/**
 * Created by Aleksandar on 3/1/2017.
 */

public class CustomListCount extends BaseAdapter {

    Context c;
    ArrayList<EventData> events;
    LayoutInflater inflater;
    String date;
    String long_click;
    String url;
    String bend;
    Intent browserIntent;


    public CustomListCount(Context c, ArrayList<EventData> events) {
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
            convertView = inflater.inflate(R.layout.single_list_two, parent, false);
        }

        TextView nameTxt = (TextView) convertView.findViewById(R.id.naziv);
        nameTxt.setText(events.get(position).getNaziv_benda());
        TextView countTxt = (TextView) convertView.findViewById(R.id.broj);
        countTxt.setText(events.get(position).getCount());


        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                bend = String.valueOf(events.get(position).getNaziv_benda());

                switch (bend) {
                    case "MARX":
                        url = "http://www.marxbend.com";
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        c.startActivity(browserIntent);
                        break;
                    case "BEND OD MARCIPANA":
                        url = "http://www.bendodmarcipana.com";
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        c.startActivity(browserIntent);
                        break;
                    case "ROKO MAROKO":
                        url = "http://www.rokomaroko.com";
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        c.startActivity(browserIntent);
                        break;
                    case "MANDARINA":
                        url = "http://www.mandarinabend.com";
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        c.startActivity(browserIntent);
                        break;
                    case "LA BOMBONJERA":
                        url = "http://www.labombonjerabend.com";
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        c.startActivity(browserIntent);
                        break;
                    case "KAFANKI":
                        url = "http://www.kafankibend.com";
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        c.startActivity(browserIntent);
                        break;
                    case "TINI MARTINI":
                        url = "http://www.tinimartinibend.com";
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        c.startActivity(browserIntent);
                        break;
                    case "MISTER TWISTER":
                        url = "http://www.mistertwisterbend.com";
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        c.startActivity(browserIntent);
                        break;
                    case "BUGI":
                        url = "http://www.bugisoundbend.com";
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        c.startActivity(browserIntent);
                        break;
                    case "LILA":
                        url = "http://www.lilabend.com";
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        c.startActivity(browserIntent);
                        break;
                    case "VENECIJA":
                        url = "http://www.venecijabend.com";
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        c.startActivity(browserIntent);
                        break;
                    case "TAPAS":
                        url = "http://www.tapasbend.com";
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        c.startActivity(browserIntent);
                        break;
                    case "RIO GRANDE":
                        url = "http://www.riograndebend.com";
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        c.startActivity(browserIntent);
                        break;
                    case "AMSTERDAM":
                        url = "http://www.amsterdambend.com";
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        c.startActivity(browserIntent);
                        break;
                    case "MAURICIUS":
                        url = "http://www.mauriciusbend.com";
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        c.startActivity(browserIntent);
                        break;
                    case "KONFETE":
                        url = "http://www.konfetebend.com";
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        c.startActivity(browserIntent);
                        break;
                    case "PARTY BUS":
                        url = "http://www.partybusbend.com";
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        c.startActivity(browserIntent);
                        break;
                    case "SO I TEKILA":
                        url = "http://www.soitekilabend.com";
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        c.startActivity(browserIntent);
                        break;
                    case "KIWI":
                        url = "http://www.kiwibend.com";
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        c.startActivity(browserIntent);
                        break;
                    case "MAČAK MIŠA":
                        url = "http://www.macakmisa.com";
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        c.startActivity(browserIntent);
                        break;
                    case "MEDITERANEO ":
                        url = "http://www.mediteraneobend.com";
                        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        c.startActivity(browserIntent);
                        //Toast.makeText(c,events.get(position).getNaziv_benda(),Toast.LENGTH_SHORT).show();
                        break;
                }


                //Toast.makeText(c, events.get(position).getNaziv_benda(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        //ITEM CLICKS


        return convertView;
    }
}