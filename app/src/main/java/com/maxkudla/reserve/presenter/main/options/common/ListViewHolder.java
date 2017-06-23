package com.maxkudla.reserve.presenter.main.options.common;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.maxkudla.reserve.R;

public class ListViewHolder extends RecyclerView.ViewHolder{
    TextView tvOptionSelect;
    TextView tvOptionPreview;
    public ListViewHolder(View itemView) {
        super(itemView);
        tvOptionPreview = (TextView) itemView.findViewById(R.id.tvOptionPreview);
        tvOptionSelect = (TextView) itemView.findViewById(R.id.tvOptionSelect);
    }
}
