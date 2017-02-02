package com.example.aleksandar.mysqldemo;

import android.widget.ArrayAdapter;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * Created by Vlatko Popovic on 02-Feb-17.
 */




    public class CustomList extends ArrayAdapter<String> {

        private final Activity context;
        private final String[] web;
        private final String[] imageId;
        public CustomList(Activity context,
                          String[] web, String[] imageId) {
            super(context, R.layout.list_single, web);
            this.context = context;
            this.web = web;
            this.imageId = imageId;

        }
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView= inflater.inflate(R.layout.list_single, null, true);
            TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

            TextView imageView = (TextView) rowView.findViewById(R.id.img);
            txtTitle.setText(web[position]);
            imageView.setText(imageId[position]);


            return rowView;
        }
    }
