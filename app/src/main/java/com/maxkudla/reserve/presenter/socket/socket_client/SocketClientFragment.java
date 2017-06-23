package com.maxkudla.reserve.presenter.socket.socket_client;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import com.maxkudla.reserve.R;
import com.maxkudla.reserve.di.common.Injector;
import com.maxkudla.reserve.di.components.ActivityComponent;
import com.maxkudla.reserve.models.client.ReserveClient;
import com.maxkudla.reserve.presenter.base.BaseFragment;
import com.maxkudla.reserve.presenter.base.BasePresenter;
import com.maxkudla.reserve.presenter.socket.SocketActivity;
import com.maxkudla.reserve.presenter.socket.book.BookATableFragment;
import com.maxkudla.reserve.presenter.socket.cancel_dialog.CancelOkListener;
import com.maxkudla.reserve.presenter.socket.socket_client.common.SocketClientAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.maxkudla.reserve.Constants.QUERY_ID;
import static com.maxkudla.reserve.Constants.STATUS;

public class SocketClientFragment extends BaseFragment implements SocketClientContract.View, CancelOkListener/*, SocketServiceListener*/ {

    private SocketClientAdapter mSocketCliAdapter;

    @Inject
    SocketClientPresenter mPresenter;

    @BindView(R.id.socketRecycler)
    RecyclerView mSocketRecycler;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.empty)
    LinearLayout empty;

    private String queryId;

    public SocketClientFragment() {
    }

    public static SocketClientFragment newInstance() {
        return new SocketClientFragment();
    }

    public static SocketClientFragment newInstance(String status) {
        SocketClientFragment socketClientFragment = new SocketClientFragment();
        Bundle args = new Bundle();
        args.putString(STATUS, status);
        socketClientFragment.setArguments(args);
        return socketClientFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.getComponent(getActivity(), ActivityComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_socket, container, false);

        mPresenter.setView(this);
        mPresenter.setRouter((SocketActivity) getActivity());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        queryId = getActivity().getIntent().getStringExtra(QUERY_ID);
        ((SocketActivity) getActivity()).resolveToolbar(this, getString(R.string.app_name));
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public boolean toolbarNavigationActive() {
        return true;
    }

    @Override
    protected void initView() {


        mSocketRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mSocketCliAdapter = new SocketClientAdapter(mOnSocketItemClickListener);
        mSocketRecycler.setAdapter(mSocketCliAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        Animation anim = new AlphaAnimation(0.5f, 1.0f);
        anim.setDuration(1500); //You can manage the time
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        empty.startAnimation(anim);
        mPresenter.getDataFromSocket();
    }

    @Override
    public void onStop() {
        super.onStop();
//        mPresenter.onStopView();
        mSocketCliAdapter.removeAll();
        mPresenter.unsubscribeFromSocket();
    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void addToRecyclerItems(List<ReserveClient> reserveClientClientRequests) {
//        mSocketCliAdapter.updateReserveRequests(reserveClientClientRequests);
    }

    @Override
    public void addToRecycerAnItem(ReserveClient reserveClientClientRequest) {
        empty.clearAnimation();
        empty.setVisibility(View.GONE);

        BookATableFragment fragment = (BookATableFragment) getFragmentManager().findFragmentByTag(BookATableFragment.class.getSimpleName());

        switch (reserveClientClientRequest.getStatus()) {
            case "offer_by_service":
                mSocketCliAdapter.updateAReserveRequest(reserveClientClientRequest);
                break;
            case "reserved_by_client":
                mPresenter.openBookFragment(reserveClientClientRequest);
                break;
            case "reserved_by_service":
                if (fragment != null) {
                    fragment.setConfirmation();
                } else {
                    mPresenter.openBookFragment(reserveClientClientRequest);
                }
                break;

            case "canceled_request_by_service":
                if (fragment != null) {
                    fragment.setCanceletion(reserveClientClientRequest.getStatus());
                } else {
                    mSocketCliAdapter.removeItem(reserveClientClientRequest);
                }
                break;
            case "closed":
            case "canceled_reservation_by_service":
            case "canceled_reservation_by_client":
                mPresenter.closeSocket();
                break;

            default:
                break;

        }

    }

    @OnClick(R.id.tvToolbarCancel)
    void onCancelToolbarClick() {

//        SendOfferModel model = new SendOfferModel();
//        model.setStatus("canceled_reservation_by_client");
//        model.setRequest(mSocketCliAdapter.getExampleId());
//        mPresenter.sendClosed(getActivity().getIntent().getStringExtra(QUERY_ID));
        mPresenter.showCancelDialog(this);
    }

    private SocketClientAdapter.OnSocketItemClickListener mOnSocketItemClickListener = (reserveRequest, onRequestSentToServer) -> mPresenter.openBookFragment(reserveRequest);

    @Override
    public void onCancelClickCallback() {
        mPresenter.sendClosed(getActivity().getIntent().getStringExtra(QUERY_ID));

    }
}
