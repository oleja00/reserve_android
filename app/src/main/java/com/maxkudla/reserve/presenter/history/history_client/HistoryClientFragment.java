package com.maxkudla.reserve.presenter.history.history_client;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maxkudla.reserve.R;
import com.maxkudla.reserve.di.common.Injector;
import com.maxkudla.reserve.di.components.ActivityComponent;
import com.maxkudla.reserve.models.client.ClientListRequestModel;
import com.maxkudla.reserve.presenter.base.BaseFragment;
import com.maxkudla.reserve.presenter.base.BasePresenter;
import com.maxkudla.reserve.presenter.history.HistoryActivity;
import com.maxkudla.reserve.presenter.history.history_client.common.HistoryClientAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class HistoryClientFragment extends BaseFragment implements HistoryClientContract.View {

    @Inject
    HistoryClientPresenter mPresenter;

    @BindView(R.id.recycler_request)
    RecyclerView recyclerRequest;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @OnClick(R.id.ibBack)
    void onBack(){
        getActivity().onBackPressed();
    }

    private HistoryClientAdapter historyClientAdapter;

    public static HistoryClientFragment newInstance() {
        return new HistoryClientFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.getComponent(getActivity(), ActivityComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request_history, container, false);

        mPresenter.setView(this);
        return view;
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((HistoryActivity)getActivity()).resolveToolbar(this, "");
    }
    @Override
    protected void initView() {

        mPresenter.getAllExistingRequests("closed, canceled_reservation_by_client, canceled_reservation_by_service");
        recyclerRequest.setLayoutManager(new LinearLayoutManager(getContext()));
        historyClientAdapter = new HistoryClientAdapter(client -> mPresenter.openClientFragment(client));
        recyclerRequest.setAdapter(historyClientAdapter);
    }

    @Override
    public void updateUi(ClientListRequestModel reserveRequests) {
        historyClientAdapter.updateReserveRequestsHistory(reserveRequests.getData());

    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

}
