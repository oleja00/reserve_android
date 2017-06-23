package com.maxkudla.reserve.presenter.socket.book;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.maxkudla.reserve.R;
import com.maxkudla.reserve.di.common.Injector;
import com.maxkudla.reserve.di.components.ActivityComponent;
import com.maxkudla.reserve.models.client.ReserveClient;
import com.maxkudla.reserve.models.service.offer.SendOfferModel;
import com.maxkudla.reserve.presenter.base.BaseFragment;
import com.maxkudla.reserve.presenter.base.BasePresenter;
import com.maxkudla.reserve.presenter.common.views.SetOfStarView;
import com.maxkudla.reserve.presenter.map_activity.MapsActivity;
import com.maxkudla.reserve.presenter.socket.SocketActivity;
import com.maxkudla.reserve.presenter.socket.book.common.adapter.BookATableAdapter;
import com.maxkudla.reserve.presenter.socket.cancel_dialog.CancelOkListener;
import com.maxkudla.reserve.presenter.socket.socket_client.common.SocketClientAdapter;
import com.maxkudla.reserve.utils.LabelUtils;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;

import static com.maxkudla.reserve.Constants.MapsConstants.LAT_LNG;


public class BookATableFragment extends BaseFragment implements BookATableContract.View, CancelOkListener {

    public static final String ID_KEY = "ID_KEY";

    @Inject
    BookATablePresenter mPresenter;

    @BindView(R.id.ivBook)
    ImageView mIvBook;
    @BindView(R.id.ivMap)
    ImageView ivMap;
    @BindView(R.id.ivDescribe)
    ImageView ivDescribe;

    @BindView(R.id.tvNameBook)
    TextView tvNameBook;
    @BindView(R.id.tvAddressBook)
    TextView tvAddressBook;
    @BindView(R.id.tvBookPrice)
    TextView tvBookPrice;
    @BindView(R.id.tvInstitutionName)
    TextView tvInstitutionName;
    @BindView(R.id.tvDescriptionBook)
    TextView tvDescriptionBook;
    @BindView(R.id.bookRecycler)
    RecyclerView bookRecycler;
    @BindView(R.id.setOfStar)
    SetOfStarView setOfStar;

    @BindView(R.id.bSendRequest)
    Button bSendRequest;

    @BindView(R.id.ivBack)
    ImageView ivDrawer;

    @BindView(R.id.tvCancel)
    TextView tvCancel;

    private boolean isReserved;

    private ReserveClient reserveClientClientRequest;
    private SocketClientAdapter.OnRequestSentToServer onRequestSentToServer;

    public void setOnRequestSentToServer(SocketClientAdapter.OnRequestSentToServer onRequestSentToServer) {
        this.onRequestSentToServer = onRequestSentToServer;
    }

    public void setReserveClientClientRequest(ReserveClient reserveClientClientRequest) {
        this.reserveClientClientRequest = reserveClientClientRequest;
    }

    public BookATableFragment() {
    }

    public static BookATableFragment newInstance(String id) {
        return new BookATableFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.getComponent(getActivity(), ActivityComponent.class).inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_atable, container, false);
        mPresenter.setView(this);
        mPresenter.setRouter((SocketActivity) getActivity());
        return view;
    }

    public void updateViews(ReserveClient r) {
        tvInstitutionName.setText(r.getService().getName());
        ivDrawer.setImageResource(R.drawable.ic_dehaze_black_24dp);
        tvNameBook.setText(r.getService().getName());
        tvAddressBook.setText(r.getService().getAddress_name());
        String price = Observable.range(0, r.getService().getPrice())
                .map(integer -> "$")
                .scan((s, s2) -> s + s2)
                .blockingLast("");
        tvBookPrice.setText(price);
        tvDescriptionBook.setText(r.getService().getAbout());
        bookRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        bookRecycler.setAdapter(new BookATableAdapter(r.getService().getOptions()));
        setOfStar.setQuantityOfStars(r.getService().getRating());
        String photo = r.getService().getPhotos().get(0);
        Picasso.with(getContext()).load(photo).into(mIvBook);
        String tumbnail = r.getService().getThumbnail();
        Picasso.with(getContext()).load(tumbnail).into(ivDescribe);
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

        ivDrawer.setOnClickListener(v -> {
            ((SocketActivity)getActivity()).openDrawerCallback();
        });



        switch (r.getStatus()){
            case "offer_by_service":
                bSendRequest.setText(R.string.order);
                bSendRequest.setEnabled(true);
                bSendRequest.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                tvCancel.setVisibility(View.INVISIBLE);

                break;
            case "reserved_by_client":
                bSendRequest.setText(R.string.waiting_confirmation);
                bSendRequest.setEnabled(false);
                bSendRequest.setBackgroundColor(Color.BLUE);
                tvCancel.setVisibility(View.VISIBLE);
                break;
            case "reserved_by_service":
                bSendRequest.setText(R.string.reserved);
                bSendRequest.setEnabled(false);
                bSendRequest.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                tvCancel.setVisibility(View.VISIBLE);
                break;

            case "canceled_request_by_service":
                bSendRequest.setText(LabelUtils.handleLabels(r.getStatus()));
                bSendRequest.setEnabled(false);
                bSendRequest.setBackgroundColor(Color.RED);
                tvCancel.setVisibility(View.INVISIBLE);

            default:
                break;
        }

    }

    @OnClick(R.id.tvCancel)
    public void onCancelClick(){
        mPresenter.showCancelDialog(this);
    }

    @OnClick(R.id.bSendRequest)
    public void sendRequest() {
//        mPresenter.openHistory();

        SendOfferModel sendOfferModel = new SendOfferModel();
        sendOfferModel.setStatus("reserved_by_client");
        sendOfferModel.setRequest(reserveClientClientRequest.get_id());
        mPresenter.sendOffer(sendOfferModel);
        isReserved = true;
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
        updateViews(reserveClientClientRequest);
    }

    @Override
    public void offerHasBeenSent() {
        bSendRequest.setText(R.string.waiting_confirmation);
        bSendRequest.setEnabled(false);
        bSendRequest.setBackgroundColor(Color.BLUE);
        tvCancel.setVisibility(View.VISIBLE);

    }

    public void setConfirmation(){
        bSendRequest.setText(R.string.reserved);
        bSendRequest.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.green));
    }

    public void setCanceletion(String status){
        bSendRequest.setText(LabelUtils.handleLabels(status));
        bSendRequest.setBackgroundColor(Color.RED);
    }

    @Override
    public void anErrorOccurred() {

    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    private String getMap(double latitude, double longitude, int width, int height) {
        return
                "https://maps.googleapis.com/maps/api/staticmap?center=" + latitude + "," + longitude + "&zoom=13&size=" + width + "x" + height + "&sensor=false&&markers=color:red%7Clabel:JOB%7C" + latitude + "," + longitude;
    }

    @Override
    public void onCancelClickCallback() {
        SendOfferModel sendOfferModel = new SendOfferModel();
        sendOfferModel.setStatus("canceled_reservation_by_client");
        sendOfferModel.setRequest(reserveClientClientRequest.get_id());
        mPresenter.sendCancel(sendOfferModel);
//        mPresenter.cancelRequest(reserveClientClientRequest.getQuery());
    }

    public boolean isReserved(){
        return isReserved;
    }

}
