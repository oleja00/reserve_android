package com.maxkudla.reserve.presenter.history.history_client.common;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maxkudla.reserve.R;
import com.maxkudla.reserve.models.client.ServiceOptions;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailHistoryClientAdapter extends RecyclerView.Adapter<DetailHistoryClientAdapter.DetailClientHolder> {

    private List<ServiceOptions> list;

    public DetailHistoryClientAdapter(List<ServiceOptions> list) {
        this.list = list;
    }

    @Override
    public DetailHistoryClientAdapter.DetailClientHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DetailHistoryClientAdapter.DetailClientHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_a_table_field, parent, false));
    }

    @Override
    public void onBindViewHolder(DetailHistoryClientAdapter.DetailClientHolder holder, int position) {
        holder.tvText.setText(list.get(position).getValue());
        holder.tvTitle.setText(list.get(position).getTitle());
        Picasso.with(holder.itemView.getContext()).load(
                list.get(position).getIcon()).into(holder.ivIconBookATableInfo);
        holder.forFirstViewOne.setVisibility(position == 0 || position == list.size() - 1 ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class DetailClientHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvText;
        ImageView ivIconBookATableInfo;
        View forFirstViewOne;
        public DetailClientHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitleOptions);
            tvText = (TextView) itemView.findViewById(R.id.tvTextOptions);
            ivIconBookATableInfo = (ImageView) itemView.findViewById(R.id.ivIconBookATableInfo);
            forFirstViewOne = itemView.findViewById(R.id.forFirstViewOne);
        }
    }

}