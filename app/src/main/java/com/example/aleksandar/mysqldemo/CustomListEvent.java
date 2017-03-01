package com.example.aleksandar.mysqldemo;

/**
 * Created by Aleksandar on 3/1/2017.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.example.aleksandar.mysqldemo.Event.EventData;
import java.util.ArrayList;


/**
 * Created by Aleksandar on 2/28/2017.
 */


public class CustomListEvent extends BaseAdapter {

    Context c;
    ArrayList<EventData> events;
    LayoutInflater inflater;

    public CustomListEvent(Context c, ArrayList<EventData> events) {
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
            convertView=inflater.inflate(R.layout.liste,parent,false);
        }

        TextView nameTxt= (TextView) convertView.findViewById(R.id.bendTxt);
        TextView eventTxt= (TextView) convertView.findViewById(R.id.eventTxt);
        TextView imeTxt= (TextView) convertView.findViewById(R.id.imeTxt);
        TextView gradTxt= (TextView) convertView.findViewById(R.id.gradTxt);
        TextView lokalTxt= (TextView) convertView.findViewById(R.id.lokalTxt);


        nameTxt.setText(events.get(position).getNaziv_benda());
        eventTxt.setText(events.get(position).getEvent());
        imeTxt.setText(events.get(position).getIme());
        gradTxt.setText(events.get(position).getGrad());
        lokalTxt.setText(events.get(position).getLokal());


        //ITEM CLICKS

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c,events.get(position).getNaziv_benda(),Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
