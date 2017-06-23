package com.maxkudla.reserve.presenter.socket.service;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.maxkudla.reserve.R;
import com.maxkudla.reserve.di.common.Injector;
import com.maxkudla.reserve.di.components.ActivityComponent;
import com.maxkudla.reserve.models.OnLineModel;
import com.maxkudla.reserve.presenter.base.BaseFragment;
import com.maxkudla.reserve.presenter.base.BasePresenter;
import com.maxkudla.reserve.presenter.socket.SocketActivity;
import com.maxkudla.reserve.presenter.socket.service.common.ServiceViewPagerAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnCheckedChanged;

import static com.maxkudla.reserve.Constants.SERVICE_ID;

public class ServiceFragment extends BaseFragment implements ServiceContract.View {

    @BindView(R.id.viewPagerService)
    ViewPager viewPagerService;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.swOnline)
    Switch swOnline;

    String serviceId;

    @Inject
    ServicePresenter mPresenter;

    public static ServiceFragment newInstance(String serviceId) {
        ServiceFragment serviceFragment = new ServiceFragment();
        Bundle args = new Bundle();
        args.putString(SERVICE_ID, serviceId);
        serviceFragment.setArguments(args);
        return serviceFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.getComponent(getActivity(), ActivityComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, container, false);


        mPresenter.setView(this);
        mPresenter.setRouter((SocketActivity) getActivity());
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((SocketActivity)getActivity()).resolveToolbar(this, "");
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
        viewPagerService.setAdapter(new ServiceViewPagerAdapter(getFragmentManager(), getContext()));
        tabLayout.setupWithViewPager(viewPagerService);
    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @OnCheckedChanged(R.id.swOnline)
    void checkedChanged(boolean isChecked){
        OnLineModel onLineModel = new OnLineModel();
        onLineModel.setOnline(isChecked);
        onLineModel.setServiceId(getArguments().getString(SERVICE_ID));
        mPresenter.setOnline(onLineModel);
    }

    @Override
    public void showOnline() {
        swOnline.setText(swOnline.isChecked()? R.string.online : R.string.offline);
        swOnline.setTextColor(swOnline.isChecked()? Color.GREEN : Color.RED);
    }
}
