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

public class OfflineListLogic extends ArrayAdapter<String> {


    private final Activity context;
    private final String[] imeBenda;
    private final String[] ime;
    private final String[] grad;
    private final String[] restoran;
    private final String[] event;
    public OfflineListLogic(Activity context, String[] imeBenda, String[] ime, String[] grad, String[] restoran,String[] event) {
        super(context, R.layout.liste, imeBenda);
        this.context = context;
        this.imeBenda = imeBenda;
        this.ime = ime;
        this.grad = grad;
        this.restoran = restoran;
        this.event = event;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.liste, parent, false);

        TextView imeBendaTxt = (TextView) rowView.findViewById(R.id.bendTxt);
        TextView imeMladenacaTxt = (TextView) rowView.findViewById(R.id.imeTxt);
        TextView gradTxt = (TextView) rowView.findViewById(R.id.gradTxt);
        TextView restoranTxt = (TextView) rowView.findViewById(R.id.lokalTxt);
        TextView eventTxt = (TextView) rowView.findViewById(R.id.checkTxt);

        imeBendaTxt.setText(imeBenda[position]);
        imeMladenacaTxt.setText(ime[position]);
        gradTxt.setText(grad[position]);
        restoranTxt.setText(restoran[position]);
        eventTxt.setText(event[position]);

        return rowView;
    }










}
