package com.example.aleksandar.mysqldemo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Vlatko Popovic on 09-Mar-17.
 */


public class OfflineSlobodniLogic extends ArrayAdapter<String> {


    private final Activity context;
    private final String[] imeBenda;

    public OfflineSlobodniLogic(Activity context, String[] imeBenda) {
        super(context, R.layout.listeslobodni, imeBenda);
        this.context = context;
        this.imeBenda = imeBenda;


    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.listeslobodni, parent, false);

        TextView imeBendaTxt = (TextView) rowView.findViewById(R.id.bendTxt);


        imeBendaTxt.setText(imeBenda[position]);


        return rowView;
    }










}