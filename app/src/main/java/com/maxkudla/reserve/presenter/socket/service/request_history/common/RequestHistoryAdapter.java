package com.maxkudla.reserve.presenter.socket.service.request_history.common;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maxkudla.reserve.R;
import com.maxkudla.reserve.models.service.ReserveService;

import java.util.ArrayList;
import java.util.List;

public class RequestHistoryAdapter extends RecyclerView.Adapter<RequestHistoryAdapter.RequestHistoryHolder> {

    private List<ReserveService> list;
    OnGuestClick onGuestClick;

    public RequestHistoryAdapter( OnGuestClick onGuestClick) {
        this.onGuestClick = onGuestClick;
        list = new ArrayList<>();
    }

    public void setReserve(ReserveService reserveService){
        list.add(reserveService);
        notifyItemInserted(list.indexOf(reserveService));
    }

    public void setList(List<ReserveService> list) {
        this.list = list;
        // TODO: 04.06.2017 check this letter
        notifyDataSetChanged();
    }

    @Override
    public RequestHistoryAdapter.RequestHistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RequestHistoryAdapter.RequestHistoryHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_request_history,parent,false));
    }

    @Override
    public void onBindViewHolder(RequestHistoryHolder holder, int position) {
//        holder.itemView.setOnClickListener(view -> onGuestClick.onGuestClick(null, 0L));
        ReserveService r = list.get(position);
        holder.tvGuestsCount.setText(String.valueOf(r.getQuery().getGuests()));
        String distance = String.valueOf(r.getDistance());
        if (distance.length() > 5) {
            holder.tvDistanceCount.setText(distance.substring(0, 4));
        } else {
            holder.tvDistanceCount.setText(distance);
        }
        holder.tvLabel.setText(r.getStatus());
        holder.tvLabel.setBackground(r.getStatus().equals("request_by_client") ?
                ContextCompat.getDrawable(holder.tvLabel.getContext(), R.drawable.background_text_green) :
                ContextCompat.getDrawable(holder.tvLabel.getContext(), R.drawable.background_text_blue));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class RequestHistoryHolder extends RecyclerView.ViewHolder {
        ImageView ivPlaceImage;
        ImageView ivIconType;
        TextView tvLabel;
        TextView tvGuestsCount;
        TextView tvDistanceCount;
        RequestHistoryHolder(View itemView) {
            super(itemView);
            ivPlaceImage = (ImageView) itemView.findViewById(R.id.ivPlaceImage);
            ivIconType = (ImageView) itemView.findViewById(R.id.ivIconTaxi);
            tvLabel = (TextView) itemView.findViewById(R.id.tvLabel);
            tvGuestsCount = (TextView) itemView.findViewById(R.id.tvGuestsCount);
            tvDistanceCount = (TextView) itemView.findViewById(R.id.tvDistanceCount);
        }
    }

    public interface OnGuestClick{
        void onGuestClick(String sId, long lId);
    }

}
