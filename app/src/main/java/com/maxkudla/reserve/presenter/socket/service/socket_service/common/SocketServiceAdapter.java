package com.maxkudla.reserve.presenter.socket.service.socket_service.common;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maxkudla.reserve.R;
import com.maxkudla.reserve.models.service.ReserveService;
import com.maxkudla.reserve.utils.LabelUtils;

import java.util.ArrayList;
import java.util.List;

public class SocketServiceAdapter extends RecyclerView.Adapter<SocketServiceAdapter.SocketHolder> {

    private List<ReserveService> mReserveServiceRequests;
    private OnSocketItemClickListener mOnSocketItemClickListener;

    public SocketServiceAdapter(OnSocketItemClickListener onSocketItemClickListener) {
        mOnSocketItemClickListener = onSocketItemClickListener;
    }

    @Override
    public SocketHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SocketHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_request_history, parent, false));
    }

    @Override
    public void onBindViewHolder(SocketHolder holder, int position) {
        ReserveService r = mReserveServiceRequests.get(position);
        holder.itemView.setOnClickListener(view ->
                mOnSocketItemClickListener.onSocketItemReservationClick(r, this::removeItem));
        holder.tvGuestsCount.setText(String.valueOf(r.getQuery().getGuests()));
        String distance = String.valueOf(r.getDistance());
        if (distance.length() > 5) {
            holder.tvDistanceCount.setText(distance.substring(0, 4));
        } else {
            holder.tvDistanceCount.setText(distance);
        }
        holder.tvLabel.setText(LabelUtils.handleLabels(r.getStatus()));
        holder.tvLabel.setBackground(r.getStatus().equals("request_by_client") ?
                ContextCompat.getDrawable(holder.tvLabel.getContext(), R.drawable.background_text_green) :
                ContextCompat.getDrawable(holder.tvLabel.getContext(), R.drawable.background_text_blue));
    }

    @Override
    public int getItemCount() {
        return mReserveServiceRequests == null ? 0 : mReserveServiceRequests.size();
    }

    public void updateReserveRequests(List<ReserveService> reserveServiceRequests) {
        if (mReserveServiceRequests == null) {
            mReserveServiceRequests = reserveServiceRequests;
        } else {
            mReserveServiceRequests.addAll(reserveServiceRequests);
        }
        notifyDataSetChanged();
    }

    public void updateAReserveRequest(ReserveService reserveServiceRequest) {
        if (mReserveServiceRequests == null) {
            mReserveServiceRequests = new ArrayList<>();
        }
        mReserveServiceRequests.add(0, reserveServiceRequest);
        notifyItemInserted(mReserveServiceRequests.indexOf(reserveServiceRequest));
    }

    public void removeItem(ReserveService reserveServiceRequest) {
        int pos = mReserveServiceRequests.indexOf(reserveServiceRequest);
        mReserveServiceRequests.remove(pos);
        notifyItemRemoved(pos);
    }

    class SocketHolder extends RecyclerView.ViewHolder {
        ImageView ivPlaceImage;
//        ImageView ivIconType;
        TextView tvLabel;
        TextView tvGuestsCount;
        TextView tvDistanceCount;

        SocketHolder(View itemView) {
            super(itemView);
            ivPlaceImage = (ImageView) itemView.findViewById(R.id.ivPlaceImage);
//            ivIconType = (ImageView) itemView.findViewById(R.id.ivIconTaxi);
            tvLabel = (TextView) itemView.findViewById(R.id.tvLabel);
            tvGuestsCount = (TextView) itemView.findViewById(R.id.tvGuestsCount);
            tvDistanceCount = (TextView) itemView.findViewById(R.id.tvDistanceCount);
        }
    }

    public interface OnSocketItemClickListener {
        void onSocketItemReservationClick(ReserveService reserveServiceRequest, OnRequestSentToClient onRequestSentToClient);
    }

    public interface OnRequestSentToClient {
        void requestSent(ReserveService reserveServiceRequest);
    }

}
