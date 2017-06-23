package com.maxkudla.reserve.presenter.socket.socket_client.common;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.maxkudla.reserve.R;
import com.maxkudla.reserve.models.client.ReserveClient;
import com.maxkudla.reserve.models.client.ServiceOptions;
import com.maxkudla.reserve.presenter.common.views.SetOfStarView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class SocketClientAdapter extends RecyclerView.Adapter<SocketClientAdapter.SocketHolder> {

    private List<ReserveClient> mReserveClientClientRequests;
    private OnSocketItemClickListener mOnSocketItemClickListener;

    public SocketClientAdapter(OnSocketItemClickListener onSocketItemClickListener) {
        mOnSocketItemClickListener = onSocketItemClickListener;
    }

    @Override
    public SocketHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SocketHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_socket, parent, false));
    }

    @Override
    public void onBindViewHolder(SocketHolder holder, int position) {
        ReserveClient r = mReserveClientClientRequests.get(position);
        holder.itemView.setOnClickListener(view -> mOnSocketItemClickListener.onSocketItemClick(r, this::removeItem));
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
                    .onErrorResumeNext(throwable -> {})
                    .map(ServiceOptions::getValue)
                    .scan((s, s2) -> s + " " + s2)
                    .blockingFirst("");
            holder.tvSubTitle.setText(cuisines);


        if(r.getOffer()!=null){
            for (int i = 0; i <r.getOffer().size() ; i++) {
                if(r.getOffer().get(i).getValueValue()>0){
                    switch (r.getOffer().get(i).getFeature()){
                        case "taxi":
                            holder.ivIconTaxi.setVisibility(View.VISIBLE);
                            Picasso.with(holder.itemView.getContext()).load(r.getOffer().get(i).getIcon()).into(holder.ivIconTaxi);
                            break;
                        case "cocktail":
                            holder.ivIconCoctail.setVisibility(View.VISIBLE);
                            Picasso.with(holder.itemView.getContext()).load(r.getOffer().get(i).getIcon()).into(holder.ivIconCoctail);
                            break;
                        case "discount":
                            holder.frameDiscount.setVisibility(View.VISIBLE);
                            Picasso.with(holder.itemView.getContext()).load(r.getOffer().get(i).getIcon()).into(holder.ivDiscount);
                            holder.tvDiscount.setText(r.getOffer().get(i).getValueValue()+"%");
                            break;
                    }
                }/*else {
                    switch (r.getOffer().get(i).getFeature()) {
                        case "taxi":
                            holder.ivIconTaxi.setVisibility(View.GONE);
                            break;
                        case "cocktail":
                            holder.ivIconCoctail.setVisibility(View.GONE);
                            break;
                        case "discount":
                            holder.tvDiscount.setVisibility(View.GONE);
                            break;
                    }
                }*/
            }
        }
    }

    @Override
    public int getItemCount() {
        return mReserveClientClientRequests == null ? 0 : mReserveClientClientRequests.size();
    }

    public void removeAll(){

        if (mReserveClientClientRequests != null){
            mReserveClientClientRequests.clear();
        }
        notifyDataSetChanged();
    }
    public void updateReserveRequests(List<ReserveClient> reserveClientClientRequests){
        if (mReserveClientClientRequests == null){
            mReserveClientClientRequests = reserveClientClientRequests;
        }else {
            mReserveClientClientRequests.addAll(reserveClientClientRequests);
        }
        notifyDataSetChanged();
    }

    public void updateAReserveRequest(ReserveClient reserveClientClientRequest){
        if (mReserveClientClientRequests == null){
            mReserveClientClientRequests = new ArrayList<>();
        }
        mReserveClientClientRequests.add(0, reserveClientClientRequest);
        notifyItemInserted(mReserveClientClientRequests.indexOf(reserveClientClientRequest));
    }

    public void removeItem(ReserveClient reserveClientClientRequest) {
        int pos = mReserveClientClientRequests.indexOf(reserveClientClientRequest);
        mReserveClientClientRequests.remove(reserveClientClientRequest);
        notifyItemRemoved(pos);
    }

    public String getExampleId() {
        return mReserveClientClientRequests.get(0).get_id();
    }

    class SocketHolder extends RecyclerView.ViewHolder {
        ImageView ivPlaceImage;
        ImageView ivIconTaxi;
        ImageView ivIconCoctail;
        TextView tvDiscount;
        TextView tvPlaceName;
        TextView tvSubTitle;
        TextView tvDistance;
        TextView tvPlaceAddress;
        SetOfStarView setOfStar;
        TextView tvPricePlace;
        ImageView ivDiscount;
        FrameLayout frameDiscount;

        SocketHolder(View itemView) {
            super(itemView);
            ivPlaceImage = (ImageView) itemView.findViewById(R.id.ivPlaceImage);
            ivIconTaxi = (ImageView) itemView.findViewById(R.id.ivIconTaxi);
            ivIconCoctail = (ImageView) itemView.findViewById(R.id.ivIconCoctail);
            tvDiscount = (TextView) itemView.findViewById(R.id.tvDiscount);
            tvPlaceName = (TextView) itemView.findViewById(R.id.tvPlaceName);
            tvSubTitle = (TextView) itemView.findViewById(R.id.tvSubTitle);
            tvDistance = (TextView) itemView.findViewById(R.id.tvDistance);
            tvPlaceAddress = (TextView) itemView.findViewById(R.id.tvPlaceAddress);
            tvPricePlace = (TextView) itemView.findViewById(R.id.tvPricePlace);
            setOfStar = (SetOfStarView) itemView.findViewById(R.id.setOfStar);
            ivDiscount = (ImageView)itemView.findViewById(R.id.ivDiscount);
            frameDiscount = (FrameLayout)itemView.findViewById(R.id.frameDiscount);
        }
    }

    public interface OnSocketItemClickListener {
        void onSocketItemClick(ReserveClient reserveClientClientRequest, OnRequestSentToServer onRequestSentToServer);
    }

    public interface OnRequestSentToServer{
        void requestSent(ReserveClient reserveClientClientRequest);
    }

}
