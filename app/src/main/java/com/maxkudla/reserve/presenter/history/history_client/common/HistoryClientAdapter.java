package com.maxkudla.reserve.presenter.history.history_client.common;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maxkudla.reserve.R;
import com.maxkudla.reserve.models.client.ReserveClient;
import com.maxkudla.reserve.models.client.ServiceOptions;
import com.maxkudla.reserve.presenter.common.views.SetOfStarView;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.reactivex.Observable;

public class HistoryClientAdapter extends RecyclerView.Adapter<HistoryClientAdapter.SocketHolder> {

    private List<ReserveClient> mReserveClientClientRequests;
    private ClientHistoryInterfaceOpen clientHistoryInterfaceOpen;

    public HistoryClientAdapter(ClientHistoryInterfaceOpen clientHistoryInterfaceOpen) {
        this.clientHistoryInterfaceOpen = clientHistoryInterfaceOpen;
    }

    @Override
    public SocketHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SocketHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_socket,parent,false));
    }

    @Override
    public void onBindViewHolder(SocketHolder holder, int position) {
        ReserveClient r = mReserveClientClientRequests.get(position);
        holder.itemView.setOnClickListener(view -> clientHistoryInterfaceOpen.openClient(r));
        holder.setOfStar.setQuantityOfStars(r.getService().getRating());
        holder.tvPricePlace.setText( Observable.range(0, r.getService().getPrice())
                .map(integer -> "$")
                .scan((s, s2) -> s + s2)
                .blockingLast(""));
        holder.tvPlaceName.setText(r.getService().getName());
        holder.tvPlaceAddress.setText(r.getService().getAddress_name());
        Picasso.with(holder.itemView.getContext()).load(r.getService().getThumbnail()).into(holder.ivPlaceImage);
        String distance = String.valueOf(r.getDistance());
        if (distance.length() > 5){
            holder.tvDistance.setText(distance.substring(0, 4));
        }else {
            holder.tvDistance.setText(distance);
        }
        String cuisines = Observable.fromIterable(r.getService().getOptions())
                .map(ServiceOptions::getValue)
                .scan((s, s2) -> s + " " + s2)
                .blockingFirst("");

        holder.tvSubTitle.setText(cuisines);
    }

    public void updateReserveRequestsHistory(List<ReserveClient> reserveClientRequests) {
        if (mReserveClientClientRequests == null) {
            mReserveClientClientRequests = reserveClientRequests;
        } else {
            mReserveClientClientRequests.addAll(reserveClientRequests);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mReserveClientClientRequests == null ? 0 : mReserveClientClientRequests.size();
    }

    class SocketHolder extends RecyclerView.ViewHolder {
        ImageView ivPlaceImage;
        ImageView ivIconType;
        TextView tvPlaceName;
        TextView tvSubTitle;
        TextView tvDistance;
        TextView tvPlaceAddress;
        SetOfStarView setOfStar;
        TextView tvPricePlace;

        SocketHolder(View itemView) {
            super(itemView);
            ivPlaceImage = (ImageView) itemView.findViewById(R.id.ivPlaceImage);
            ivIconType = (ImageView) itemView.findViewById(R.id.ivIconTaxi);
            tvPlaceName = (TextView) itemView.findViewById(R.id.tvPlaceName);
            tvSubTitle = (TextView) itemView.findViewById(R.id.tvSubTitle);
            tvDistance = (TextView) itemView.findViewById(R.id.tvDistance);
            tvPlaceAddress = (TextView) itemView.findViewById(R.id.tvPlaceAddress);
            tvPricePlace = (TextView) itemView.findViewById(R.id.tvPricePlace);
            setOfStar = (SetOfStarView) itemView.findViewById(R.id.setOfStar);
        }
    }

    public interface ClientHistoryInterfaceOpen{
        void openClient(ReserveClient client);
    }

}
