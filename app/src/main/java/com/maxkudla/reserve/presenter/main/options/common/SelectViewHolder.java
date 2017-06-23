package com.maxkudla.reserve.presenter.main.options.common;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.maxkudla.reserve.R;

public class SelectViewHolder extends RecyclerView.ViewHolder{
    TextView tvOptionSelect;
//    Spinner spOptionSelect;
    TextView tvDefault;
    public SelectViewHolder(View itemView) {
        super(itemView);
        tvOptionSelect = (TextView) itemView.findViewById(R.id.tvOptionSelect);
//        spOptionSelect = (Spinner) itemView.findViewById(R.id.spOptionSelect);
        tvDefault = (TextView)itemView.findViewById(R.id.tvDefault);

    }
}
