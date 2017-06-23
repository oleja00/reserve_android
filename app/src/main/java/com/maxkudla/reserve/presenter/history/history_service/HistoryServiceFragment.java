package com.maxkudla.reserve.presenter.history.history_service;

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
import com.maxkudla.reserve.models.service.ServiceListRequestModel;
import com.maxkudla.reserve.presenter.base.BaseFragment;
import com.maxkudla.reserve.presenter.base.BasePresenter;
import com.maxkudla.reserve.presenter.history.HistoryActivity;
import com.maxkudla.reserve.presenter.history.history_service.common.HistoryServiceAdapter;

import javax.inject.Inject;

import butterknife.BindView;

public class HistoryServiceFragment extends BaseFragment implements HistoryServiceContract.View {

    @Inject
    HistoryServicePresenter mPresenter;

    @BindView(R.id.recycler_request)
    RecyclerView recyclerRequest;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private HistoryServiceAdapter historyServiceAdapter;

    public static HistoryServiceFragment newInstance() {
        return new HistoryServiceFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.getComponent(getActivity(), ActivityComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request_service_history, container, false);

        mPresenter.setView(this);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((HistoryActivity)getActivity()).resolveToolbar(this, "");
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
        mPresenter.getAllExistingRequests("canceled_reservation_by_client,canceled_reservation_by_service,closed");
        recyclerRequest.setLayoutManager(new LinearLayoutManager(getContext()));
        historyServiceAdapter = new HistoryServiceAdapter(service -> mPresenter.openServiceFragment(service));
        recyclerRequest.setAdapter(historyServiceAdapter);
    }

    @Override
    public void updateUi(ServiceListRequestModel reserveRequests) {
        historyServiceAdapter.updateReserveRequestsHistory(reserveRequests.getData());
    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

}
