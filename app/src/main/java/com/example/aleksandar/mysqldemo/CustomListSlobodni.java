package com.example.aleksandar.mysqldemo;

import android.content.Context;
import android.content.Intent;
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

public class CustomListSlobodni extends BaseAdapter {

    Context c;
    ArrayList<EventData> events;
    LayoutInflater inflater;
    String date;
    String long_click;

    public CustomListSlobodni(Context c, ArrayList<EventData> events) {
        this.c = c;
        this.events = events;

        //INITIALIE
        inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.listeslobodni,parent,false);
        }

        TextView nameTxt= (TextView) convertView.findViewById(R.id.bendTxt);
        nameTxt.setText(events.get(position).getNaziv_benda());

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                date = SlobodniBendovi.sharedValue;
                long_click = events.get(position).getNaziv_benda();
                Intent myIntent = new Intent(c, UpisIzKalendara.class);
                myIntent.putExtra("Ime", long_click);
                myIntent.putExtra("Datum", date);
                c.startActivity(myIntent);
                //Toast.makeText(c,events.get(position).getNaziv_benda(),Toast.LENGTH_SHORT).show();
                return false;
            }
        });



        //ITEM CLICKS


        return convertView;
    }
}
