package com.example.aleksandar.mysqldemo.Recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aleksandar.mysqldemo.Event.EventData;
import com.example.aleksandar.mysqldemo.R;

import java.util.ArrayList;

/**
 * Created by Aleksandar on 2/16/2017.
 */

public class Adapter extends RecyclerView.Adapter<Holder> {
    Context c;
    ArrayList<EventData> events;

    public Adapter(Context c, ArrayList<EventData> events) {
        this.c = c;
        this.events = events;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(c).inflate(R.layout.model, parent, false);
        Holder h = new Holder(v);
        return h;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.eventTxt.setText(events.get(position).getEvent());
        holder.imeTxt.setText(events.get(position).getIme());
        holder.gradTxt.setText(events.get(position).getGrad());
        holder.lokalTxt.setText(events.get(position).getLokal());

    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
