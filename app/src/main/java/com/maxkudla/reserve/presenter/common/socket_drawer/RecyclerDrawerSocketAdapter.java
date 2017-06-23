package com.maxkudla.reserve.presenter.common.socket_drawer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maxkudla.reserve.R;
import com.maxkudla.reserve.models.menu.MenuItem;

import java.util.List;

import io.reactivex.Observable;

class RecyclerDrawerSocketAdapter extends RecyclerView.Adapter<RecyclerDrawerSocketAdapter.RecyclerDrawerHolder> {

    private List<MenuItem> items;
    private OnItemMenuClick mOnItemMenuClick;

    RecyclerDrawerSocketAdapter(List<MenuItem> items, OnItemMenuClick onItemMenuClick) {
        this.items = items;
        mOnItemMenuClick = onItemMenuClick;
    }

    @Override
    public RecyclerDrawerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerDrawerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_drawer, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerDrawerHolder holder, int position) {
        holder.tvDrawerText.setText(items.get(position).getMenuName());
        holder.ivDrawerIcon.setImageResource(items.get(position).getImg());
        holder.ivDrawerIcon.setScaleX(position==items.size()-1 ? -1 : 1);
        holder.itemView.setEnabled(items.get(position).isCanBeChoosen());
        holder.vForPosition.setVisibility(position == 0 ? View.GONE : View.VISIBLE);
        holder.itemView.setOnClickListener(view -> mOnItemMenuClick.selectItem(position));
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    void updateView(MenuItem menuItem) {
        Observable.fromIterable(items)
                .filter(item -> !item.isCanBeChoosen())
                .map(item -> {
                    item.setCanBeChoosen(true);
                    return item;
                })
                .subscribe(item -> {
                    notifyItemChanged(item.getPosition());
                    menuItem.setCanBeChoosen(false);
                    notifyItemChanged(menuItem.getPosition());
                });

    }

    class RecyclerDrawerHolder extends RecyclerView.ViewHolder {

        TextView tvDrawerText;
        ImageView ivDrawerIcon;
        View vForPosition;

        RecyclerDrawerHolder(View itemView) {
            super(itemView);
            vForPosition = itemView.findViewById(R.id.vForPosition);
            tvDrawerText = (TextView) itemView.findViewById(R.id.tvDrawerText);
            ivDrawerIcon = (ImageView) itemView.findViewById(R.id.ivDrawerIcon);
        }
    }

    interface OnItemMenuClick {
        void selectItem(int position);
    }
}
