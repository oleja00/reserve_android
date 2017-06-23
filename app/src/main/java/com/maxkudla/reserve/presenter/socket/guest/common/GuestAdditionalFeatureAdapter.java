package com.maxkudla.reserve.presenter.socket.guest.common;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maxkudla.reserve.R;

import java.util.List;

public class GuestAdditionalFeatureAdapter extends RecyclerView.Adapter<GuestAdditionalFeatureAdapter.GuestHolder> {

    private List<String> items;

    public GuestAdditionalFeatureAdapter(List<String> items) {
        this.items = items;
    }

    @Override
    public GuestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GuestHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_options_switcher, parent, false));
    }

    @Override
    public void onBindViewHolder(GuestHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return items == null ? 10 : items.size();
    }

    class GuestHolder extends RecyclerView.ViewHolder {

        public GuestHolder(View itemView) {
            super(itemView);
        }
    }

}
