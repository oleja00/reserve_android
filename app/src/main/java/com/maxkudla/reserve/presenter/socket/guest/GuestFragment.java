package com.maxkudla.reserve.presenter.socket.guest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maxkudla.reserve.R;
import com.maxkudla.reserve.di.common.Injector;
import com.maxkudla.reserve.di.components.ActivityComponent;
import com.maxkudla.reserve.models.service.ReserveService;
import com.maxkudla.reserve.models.service.offer.OfferModel;
import com.maxkudla.reserve.models.service.offer.SendOfferModel;
import com.maxkudla.reserve.presenter.base.BaseFragment;
import com.maxkudla.reserve.presenter.base.BasePresenter;
import com.maxkudla.reserve.presenter.socket.SocketActivity;
import com.maxkudla.reserve.presenter.socket.service.request_history.RequestHistoryFragment;
import com.maxkudla.reserve.presenter.socket.service.socket_service.common.SocketServiceAdapter;
import com.maxkudla.reserve.utils.LabelUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class GuestFragment extends BaseFragment implements GuestContract.View {

    public static final String GUEST_KEY = "GUEST_KEY";
    @Inject
    GuestPresenter mPresenter;

    @BindView(R.id.tvNoteFromClient)
    TextView tvNoteFromClient;
    @BindView(R.id.tvLabel)
    TextView tvLabel;
    @BindView(R.id.tvCancel)
    TextView tvCancel;
    @BindView(R.id.ivGuestImage)
    ImageView ivGuestImage;
    //    @BindView(R.id.ivIcon)
//    ImageView ivIcon;
    @BindView(R.id.swcWelcomeDrink)
    SwitchCompat swcWelcomeDrink;
    @BindView(R.id.swcTaxi)
    SwitchCompat swcTaxi;

    @BindView(R.id.bSendOffer)
    Button bSendOffer;
    @BindView(R.id.additional_features_layout)
    RelativeLayout rlAdditionalFeatures;

    @BindView(R.id.guestRecycler)
    RecyclerView mGuestRecycler;
    @BindView(R.id.ivBack)
    ImageView ivDrawer;

    @BindView(R.id.tvOptionPreview)
    EditText etOptionPreview;

    private ReserveService reserveServiceRequest;
    private SocketServiceAdapter.OnRequestSentToClient onRequestSentToClient;

    public void setOnRequestSentToClient(SocketServiceAdapter.OnRequestSentToClient onRequestSentToClient) {
        this.onRequestSentToClient = onRequestSentToClient;
    }

    public void setReserveServiceRequest(ReserveService reserveServiceRequest) {
        this.reserveServiceRequest = reserveServiceRequest;
    }

    public static GuestFragment newInstance(String id) {
//        GuestFragment fragment = new GuestFragment();
//        Bundle args = new Bundle();
//        args.putString(GUEST_KEY, id);
//        fragment.setArguments(args);
        return new GuestFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.getComponent(getActivity(), ActivityComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guest, container, false);

        mPresenter.setView(this);
        mPresenter.setRouter((SocketActivity) getActivity());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        tvLabel.setText(reserveServiceRequest.getStatus());
        ivDrawer.setImageResource(R.drawable.ic_dehaze_black_24dp);
        tvLabel.setText(LabelUtils.handleLabels(reserveServiceRequest.getStatus()));
        String distance = String.valueOf(reserveServiceRequest.getDistance());
        if (distance.length() > 5) {
            ((TextView) view.findViewById(R.id.tvDistanceCount)).setText(distance.substring(0, 4));
        } else {
            ((TextView) view.findViewById(R.id.tvDistanceCount)).setText(distance);
        }
//        ((TextView) view.findViewById(R.id.tvGuestsCount))
//                .setText(String.valueOf(reserveServiceRequest.getQuery().getGuests()));

        ivDrawer.setOnClickListener(v -> {
            ((SocketActivity) getActivity()).openDrawerCallback();
        });
    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }

    @Override
    public boolean toolbarNavigationActive() {
        return false;
    }

    @Override
    protected void initView() {
//        mGuestRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
//        mGuestRecycler.setAdapter(new GuestAdditionalFeatureAdapter(null));

        if (reserveServiceRequest.getStatus().equals("request_by_client")) {
            bSendOffer.setText("Send Offer");
            bSendOffer.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.green));
            rlAdditionalFeatures.setVisibility(View.VISIBLE);
            tvLabel.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.background_text_green));

        } else {
            bSendOffer.setText("Deal Order");
            bSendOffer.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
            rlAdditionalFeatures.setVisibility(View.GONE);
            tvCancel.setVisibility(View.VISIBLE);
            tvLabel.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.background_text_blue));
        }
    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void offerHasBeenSent() {
        onRequestSentToClient.requestSent(reserveServiceRequest);
        String tag = "android:switcher:" + R.id.viewPagerService + ":" + 1;
        RequestHistoryFragment f = (RequestHistoryFragment) getFragmentManager().findFragmentByTag(tag);
        if (f != null && reserveServiceRequest.getStatus().equals("reserved_by_service")) {
            f.updateUi();
        }
        getActivity().onBackPressed();
    }

    @Override
    public void anErrorOccurred() {

    }

    @OnClick(R.id.tvCancel)
    void cancelss() {
        // TODO: 13.06.2017 Check of request is right
        SendOfferModel model = new SendOfferModel();
        model.setStatus("canceled_request_by_service");
        model.setRequest(reserveServiceRequest.get_id());
        mPresenter.sendClosed(model);
    }

    @OnClick(R.id.bSendOffer)
    void onSendOfferClick() {
        SendOfferModel sendOfferModel = new SendOfferModel();
        sendOfferModel.setStatus(reserveServiceRequest.getStatus().equals("request_by_client") ?
                "offer_by_service" : "reserved_by_service");
        sendOfferModel.setRequest(reserveServiceRequest.get_id());
        if (reserveServiceRequest.getStatus().equals("request_by_client")) {
            OfferModel offerModel = new OfferModel();
            offerModel.setCocktail(swcWelcomeDrink.isChecked()? 1:0);
            offerModel.setTaxi(swcTaxi.isChecked()? 1:0);
            offerModel.setDiscount(etOptionPreview.getText().toString()==null? 0d : Double.parseDouble(etOptionPreview.getText().toString()));
            sendOfferModel.setOffer(offerModel);
        }

        mPresenter.sendOffer(sendOfferModel);
    }
}
