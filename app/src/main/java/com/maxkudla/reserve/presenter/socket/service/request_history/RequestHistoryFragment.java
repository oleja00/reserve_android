package com.maxkudla.reserve.presenter.socket.service.request_history;

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
import com.maxkudla.reserve.presenter.socket.service.request_history.common.RequestHistoryAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class RequestHistoryFragment extends BaseFragment implements RequestHistoryContract.View {

    @Inject
    RequestHistoryPresenter mPresenter;

    @BindView(R.id.recycler_request)
    RecyclerView recyclerHistoryRequest;

    private RequestHistoryAdapter mAdapter;

    public static RequestHistoryFragment newInstance() {
        return new RequestHistoryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.getComponent(getActivity(), ActivityComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request, container, false);

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
        recyclerHistoryRequest.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new RequestHistoryAdapter((sId, lId) -> mPresenter.onGuestClick(sId, lId));
        recyclerHistoryRequest.setAdapter(mAdapter);
        updateUi();
    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public  void updateRequests(List<ReserveService> requests){
        mAdapter.setList(requests);
    }

    public void updateUi() {
        mPresenter.sendRequestToUpdate("reserved_by_service");
    }
}
