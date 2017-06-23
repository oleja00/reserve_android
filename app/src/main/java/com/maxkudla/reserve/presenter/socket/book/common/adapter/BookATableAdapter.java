package com.maxkudla.reserve.presenter.socket.book.common.adapter;

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

public class BookATableAdapter extends RecyclerView.Adapter<BookATableAdapter.BookATableHolder> {

    private List<ServiceOptions> list;

    public BookATableAdapter(List<ServiceOptions> list) {
        this.list = list;
    }

    @Override
    public BookATableHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookATableHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_a_table_field, parent, false));
    }

    @Override
    public void onBindViewHolder(BookATableHolder holder, int position) {
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

    class BookATableHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvText;
        ImageView ivIconBookATableInfo;
        View forFirstViewOne;
        public BookATableHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitleOptions);
            tvText = (TextView) itemView.findViewById(R.id.tvTextOptions);
            ivIconBookATableInfo = (ImageView) itemView.findViewById(R.id.ivIconBookATableInfo);
            forFirstViewOne = itemView.findViewById(R.id.forFirstViewOne);
        }
    }

}
