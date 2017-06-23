package com.maxkudla.reserve.presenter.history.history_service.common;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maxkudla.reserve.R;
import com.maxkudla.reserve.models.client.ReserveClient;
import com.maxkudla.reserve.models.service.ReserveService;
import com.maxkudla.reserve.utils.LabelUtils;

import java.util.List;

public class HistoryServiceAdapter extends RecyclerView.Adapter<HistoryServiceAdapter.SocketHolder> {

    private List<ReserveService> mReserveServiceRequests;
    private ServiceHistoryInterfaceOpen serviceHistoryInterfaceOpen;

    public HistoryServiceAdapter(ServiceHistoryInterfaceOpen serviceHistoryInterfaceOpen) {
        this.serviceHistoryInterfaceOpen = serviceHistoryInterfaceOpen;
    }

    @Override
    public HistoryServiceAdapter.SocketHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HistoryServiceAdapter.SocketHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_request_history, parent, false));
    }

    @Override
    public void onBindViewHolder(HistoryServiceAdapter.SocketHolder holder, int position) {
        ReserveService r = mReserveServiceRequests.get(position);
        holder.itemView.setOnClickListener(view ->
                serviceHistoryInterfaceOpen.openService(r));
        holder.tvGuestsCount.setText(String.valueOf(r.getQuery().getGuests()));
        String distance = String.valueOf(r.getDistance());
        if (distance.length() > 5) {
            holder.tvDistanceCount.setText(distance.substring(0, 4));
        } else {
            holder.tvDistanceCount.setText(distance);
        }
        holder.tvLabel.setText(LabelUtils.handleLabels(r.getStatus()));
        // TODO: uncoment it 
//        holder.tvLabel.setText(LabelUtils.handleLabels(r.getStatus()));
        holder.tvLabel.setBackground(r.getStatus().equals("search_request") ?
                ContextCompat.getDrawable(holder.tvLabel.getContext(), R.drawable.background_text_green) :
                ContextCompat.getDrawable(holder.tvLabel.getContext(), R.drawable.background_text_blue));
    }

    @Override
    public int getItemCount() {
        return mReserveServiceRequests == null ? 0 : mReserveServiceRequests.size();
    }


    public void updateReserveRequestsHistory(List<ReserveService> reserveServiceRequests) {
        if (mReserveServiceRequests == null) {
            mReserveServiceRequests = reserveServiceRequests;
        } else {
            mReserveServiceRequests.addAll(reserveServiceRequests);
        }
        notifyDataSetChanged();
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

    public interface ServiceHistoryInterfaceOpen {
        void openService(ReserveService service);
    }
}
