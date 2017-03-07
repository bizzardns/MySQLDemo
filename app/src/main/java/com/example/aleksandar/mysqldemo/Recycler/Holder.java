package com.example.aleksandar.mysqldemo.Recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.aleksandar.mysqldemo.R;

/**
 * Created by Aleksandar on 2/16/2017.
 */

public class Holder extends RecyclerView.ViewHolder {
    TextView eventTxt, imeTxt, gradTxt, lokalTxt, bendTxt;

    public Holder(View itemView) {
        super(itemView);
        bendTxt = (TextView) itemView.findViewById(R.id.bendTxt);

        imeTxt = (TextView) itemView.findViewById(R.id.imeTxt);
        gradTxt = (TextView) itemView.findViewById(R.id.gradTxt);
        lokalTxt = (TextView) itemView.findViewById(R.id.lokalTxt);

    }
}
