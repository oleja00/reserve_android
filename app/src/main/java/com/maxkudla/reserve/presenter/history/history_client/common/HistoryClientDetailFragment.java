package com.maxkudla.reserve.presenter.history.history_client.common;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.maxkudla.reserve.R;
import com.maxkudla.reserve.models.client.ReserveClient;
import com.maxkudla.reserve.presenter.common.views.SetOfStarView;
import com.maxkudla.reserve.presenter.map_activity.MapsActivity;
import com.maxkudla.reserve.utils.LabelUtils;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

import static com.maxkudla.reserve.Constants.MapsConstants.LAT_LNG;

public class HistoryClientDetailFragment extends Fragment {


    @BindView(R.id.ivBook)
    ImageView mIvBook;
    @BindView(R.id.ivMap)
    ImageView ivMap;

    @BindView(R.id.tvNameBook)
    TextView tvNameBook;
    @BindView(R.id.tvAddressBook)
    TextView tvAddressBook;
    @BindView(R.id.tvBookPrice)
    TextView tvBookPrice;
    @BindView(R.id.tvDescriptionBook)
    TextView tvDescriptionBook;
    @BindView(R.id.bookRecycler)
    RecyclerView bookRecycler;
    @BindView(R.id.setOfStar)
    SetOfStarView setOfStar;

    @BindView(R.id.bSendRequest)
    Button bSendRequest;

    @OnClick(R.id.ivBack)
    public void onClick(){
        getActivity().onBackPressed();
    }

    @BindView(R.id.tvCancel)
    TextView tvCancel;


    private ReserveClient reserveClient;

    public void setReserveClientClientRequest(ReserveClient client) {
        this.reserveClient = client;
    }

    public HistoryClientDetailFragment() {
    }

    public static HistoryClientDetailFragment newInstance(String id) {
        return new HistoryClientDetailFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_atable, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateViews(reserveClient);
    }

    public void updateViews(ReserveClient r) {

        tvNameBook.setText(r.getService().getName());
        tvAddressBook.setText(r.getService().getAddress_name());
        String price = Observable.range(0, r.getService().getPrice())
                .map(integer -> "$")
                .scan((s, s2) -> s + s2)
                .blockingLast("");
        tvBookPrice.setText(price);
        tvDescriptionBook.setText(r.getService().getAbout());
        bookRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        bookRecycler.setAdapter(new DetailHistoryClientAdapter(r.getService().getOptions()));
        setOfStar.setQuantityOfStars(r.getService().getRating());
        String photo = r.getService().getPhotos().get(0);
        Picasso.with(getContext()).load(photo).into(mIvBook);
        bSendRequest.setEnabled(false);
        bSendRequest.setBackgroundColor(Color.RED);
        bSendRequest.setText(LabelUtils.handleLabels(r.getStatus()));
        ivMap.post(() -> {
            Picasso.with(getContext())
                    .load(getMap(r.getService().getLatitude(), r.getService().getLongitude(), ivMap.getWidth(), ivMap.getHeight()))
                    .into(ivMap); // code you want to run when view is visible for the first time

            ivMap.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                LatLng latLng = new LatLng(r.getService().getLatitude(), r.getService().getLongitude());
                intent.putExtra(LAT_LNG, latLng);
                startActivity(intent);
            });
        });

    }


    private String getMap(double latitude, double longitude, int width, int height) {
        return
                "https://maps.googleapis.com/maps/api/staticmap?center=" + latitude + "," + longitude + "&zoom=13&size=" + width + "x" + height + "&sensor=false&&markers=color:red%7Clabel:JOB%7C" + latitude + "," + longitude;
    }


}