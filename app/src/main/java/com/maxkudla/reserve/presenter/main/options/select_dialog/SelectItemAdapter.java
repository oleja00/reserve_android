package com.maxkudla.reserve.presenter.main.options.select_dialog;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maxkudla.reserve.R;
import com.maxkudla.reserve.models.options.Item;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Oleja on 20.05.2017.
 */

public class SelectItemAdapter extends RecyclerView.Adapter<SelectItemAdapter.ViewHodler> {

    List<Item> itemList;

    public SelectItemAdapter(List <Item> items) {
        itemList = items;
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_select, parent, false);
        return new ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(ViewHodler holder, int position) {
        holder.tvSelect.setText(itemList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHodler extends RecyclerView.ViewHolder{

        @BindView(R.id.tvItemSelect)
        TextView tvSelect;
        public ViewHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
