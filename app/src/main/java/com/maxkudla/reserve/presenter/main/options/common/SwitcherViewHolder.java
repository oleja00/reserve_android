package com.maxkudla.reserve.presenter.main.options.common;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.TextView;

import com.maxkudla.reserve.R;

public class SwitcherViewHolder extends RecyclerView.ViewHolder {
    TextView tvOptionSwitcher;
    SwitchCompat swcOptionSwitcher;
    public SwitcherViewHolder(View itemView) {
        super(itemView);
        tvOptionSwitcher = (TextView) itemView.findViewById(R.id.tvOptionSwitcher);
        swcOptionSwitcher = (SwitchCompat) itemView.findViewById(R.id.swcOptionSwitcher);
    }
}
