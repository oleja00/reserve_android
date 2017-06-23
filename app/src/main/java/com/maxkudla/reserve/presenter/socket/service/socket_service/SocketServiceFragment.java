package com.maxkudla.reserve.presenter.socket.service.socket_service;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maxkudla.reserve.R;
import com.maxkudla.reserve.di.common.Injector;
import com.maxkudla.reserve.di.components.ActivityComponent;
import com.maxkudla.reserve.models.service.ReserveService;
import com.maxkudla.reserve.presenter.base.BaseFragment;
import com.maxkudla.reserve.presenter.base.BasePresenter;
import com.maxkudla.reserve.presenter.socket.SocketActivity;
import com.maxkudla.reserve.presenter.socket.service.request_history.RequestHistoryFragment;
import com.maxkudla.reserve.presenter.socket.service.socket_service.common.SocketServiceAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class SocketServiceFragment extends BaseFragment implements SocketServiceContract.View/*, SocketServiceListener*/ {

    private SocketServiceAdapter mSocketCliAdapter;

    @Inject
    SocketServicePresenter mPresenter;

    @BindView(R.id.socketRecycler)
    RecyclerView mSocketRecycler;

    public SocketServiceFragment() {
    }

    public static SocketServiceFragment newInstance() {
        return new SocketServiceFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.getComponent(getActivity(), ActivityComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_socket_service, container, false);

        mPresenter.setView(this);
        mPresenter.setRouter((SocketActivity) getActivity());
        return view;
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
        mSocketRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mSocketCliAdapter = new SocketServiceAdapter(mOnSocketItemClickListener);
        mSocketRecycler.setAdapter(mSocketCliAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.getDataFromSocket();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.unsubscribeFromSocket();
    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void addToRecyclerItems(List<ReserveService> reserveServiceRequests) {
        mSocketCliAdapter.updateReserveRequests(reserveServiceRequests);
    }

    @Override
    public void addToRecycerAnItem(ReserveService reserveServiceRequest) {
        switch (reserveServiceRequest.getStatus()) {
            case "request_by_client":
            case "reserved_by_client":
                mSocketCliAdapter.updateAReserveRequest(reserveServiceRequest);
                break;
            case "canceled_request_by_client":
                mSocketCliAdapter.removeItem(reserveServiceRequest);
                break;

            default:
                break;

        }

    }

    void sendDataToHistoryRequestFragment() {
        String tag = "android:switcher:" + R.id.viewPagerService + ":" + 1;
        RequestHistoryFragment f = (RequestHistoryFragment) getFragmentManager().findFragmentByTag(tag);
        if (f != null) {
            f.updateUi();
        }
    }

    private SocketServiceAdapter.OnSocketItemClickListener mOnSocketItemClickListener
            = (reserveRequest, onRequestSentToClient) -> mPresenter.openBookFragment(reserveRequest, onRequestSentToClient);
}
