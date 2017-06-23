package com.maxkudla.reserve.presenter.common.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maxkudla.reserve.R;
import com.maxkudla.reserve.models.categories.Category;
import com.maxkudla.reserve.presenter.main.categories.CategoriesClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Developer on 29.04.2017.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private List<Category> mList;
    private CategoriesClickListener mListener;

    public CategoriesAdapter(List<Category> list, CategoriesClickListener listener) {
        mList = list;
        mListener = listener;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categories, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.tvCategories.setText(mList.get(position).getName());
        Picasso.with(holder.itemView.getContext()).load(mList.get(position).getImage()).into(holder.ivCategories);


        if (mList.get(position).getCount() > 0) {
            holder.tvCategoriesCount.setText(holder.itemView.getContext().getString(R.string.available)+mList.get(position).getCount());
            holder.itemView.setOnClickListener(view -> mListener.onCategoryClick(mList.get(position).getId()));
            holder.alphaView.setVisibility(View.GONE);
        }else {
            holder.tvCategoriesCount.setText(R.string.coming_soon);
            holder.alphaView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivCategoriesImage)
        ImageView ivCategories;
        @BindView(R.id.tvCategoriesName)
        TextView tvCategories;
        @BindView(R.id.tvCategoriesCount)
        TextView tvCategoriesCount;
        @BindView(R.id.alphaView)
        View alphaView;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

}
