package com.maxkudla.reserve.presenter.main.options.common;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.maxkudla.reserve.R;
import com.maxkudla.reserve.models.options.Item;

import java.util.List;

/**
 * Created by Oleja on 13.05.2017.
 */

public class SpinnerAdapter extends BaseAdapter {

    List<Item> itemList;

    public SpinnerAdapter(List <Item> items) {
        itemList = items;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_option_spinner, null);
        TextView spinerItemText = (TextView) convertView.findViewById(R.id.tvSpinner);
        spinerItemText.setText(itemList.get(position).getTitle());
        return convertView;
    }
}
