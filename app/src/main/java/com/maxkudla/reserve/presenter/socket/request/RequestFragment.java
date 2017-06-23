package com.maxkudla.reserve.presenter.socket.request;

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
import com.maxkudla.reserve.presenter.base.BaseFragment;
import com.maxkudla.reserve.presenter.base.BasePresenter;
import com.maxkudla.reserve.presenter.socket.SocketActivity;
import com.maxkudla.reserve.presenter.socket.request.common.RequestAdapter;

import javax.inject.Inject;

import butterknife.BindView;

public class RequestFragment extends BaseFragment implements RequestContract.View{

    @Inject
    RequestPresenter mPresenter;

    @BindView(R.id.recycler_request)
    RecyclerView recyclerRequest;


    public static RequestFragment newInstance() {
        return new RequestFragment();
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
        recyclerRequest.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerRequest.setAdapter(new RequestAdapter());
    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

}
