package com.maxkudla.reserve.presenter.socket.request.common;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maxkudla.reserve.R;

import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestHolder> {

    List<String> list;

    @Override
    public RequestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RequestHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_request,parent,false));
    }

    @Override
    public void onBindViewHolder(RequestHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        // TODO: 16.05.2017 delete 1 
        return list == null ? 10 : list.size();
    }

    class RequestHolder extends RecyclerView.ViewHolder{

        public RequestHolder(View itemView) {
            super(itemView);
        }
    }

}
