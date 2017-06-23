package com.maxkudla.reserve.presenter.history.history_service.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View;

import com.maxkudla.reserve.R;
import com.maxkudla.reserve.models.service.ReserveService;
import com.maxkudla.reserve.presenter.history.HistoryActivity;
import com.maxkudla.reserve.utils.LabelUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryServiceDetailFragment extends Fragment {

    public static final String GUEST_KEY = "GUEST_KEY";


    @BindView(R.id.tvNoteFromClient)
    TextView tvNoteFromClient;
    @BindView(R.id.tvLabel)
    TextView tvLabel;
    @BindView(R.id.ivGuestImage)
    ImageView ivGuestImage;
//    @BindView(R.id.ivIcon)
//    ImageView ivIcon;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.bSendOffer)
    Button bSendOffer;

    private ReserveService reserveServiceRequest;

    public void setReserveServiceRequest(ReserveService reserveServiceRequest) {
        this.reserveServiceRequest = reserveServiceRequest;
    }

    public static HistoryServiceDetailFragment newInstance(String id) {
        return new HistoryServiceDetailFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        tvLabel.setText(reserveServiceRequest.getStatus());
//         TODO: uncoment it
        tvLabel.setText(LabelUtils.handleLabels(reserveServiceRequest.getStatus()));
        String distance = String.valueOf(reserveServiceRequest.getDistance());
        if (distance.length() > 5) {
            ((TextView) view.findViewById(R.id.tvDistanceCount)).setText(distance.substring(0, 4));
        } else {
            ((TextView) view.findViewById(R.id.tvDistanceCount)).setText(distance);
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((HistoryActivity) getActivity()).resolveToolbar(toolbar, "");
    }

}
