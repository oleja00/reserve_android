package com.maxkudla.reserve.presenter.main.options.common;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.maxkudla.reserve.R;

public class LabelViewHolder extends RecyclerView.ViewHolder{
    TextView tvLabelDatum;
    public LabelViewHolder(View itemView) {
        super(itemView);
        tvLabelDatum = (TextView)itemView.findViewById(R.id.tvLabelDatum);
    }
}
