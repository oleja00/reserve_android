package com.maxkudla.reserve.presenter.socket.book.common;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.maxkudla.reserve.R;

public class ImageGallaryAdapter extends RecyclerView.Adapter<ImageGallaryAdapter.ImageHolder> {

    @Override
    public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_gallery, parent, false);
        return new  ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ImageHolder extends RecyclerView.ViewHolder {
    ImageView ivImage;
        public ImageHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.image_gallary);
        }
    }
}
