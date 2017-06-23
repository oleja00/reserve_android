package com.maxkudla.reserve.presenter.history;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;

import com.maxkudla.reserve.App;
import com.maxkudla.reserve.Constants;
import com.maxkudla.reserve.R;
import com.maxkudla.reserve.di.common.HasComponent;
import com.maxkudla.reserve.di.common.Injector;
import com.maxkudla.reserve.di.components.ActivityComponent;
import com.maxkudla.reserve.di.components.DaggerActivityComponent;
import com.maxkudla.reserve.models.client.ReserveClient;
import com.maxkudla.reserve.models.service.ReserveService;
import com.maxkudla.reserve.presenter.base.BaseActivity;
import com.maxkudla.reserve.presenter.base.BaseFragment;
import com.maxkudla.reserve.presenter.history.history_client.HistoryClientFragment;
import com.maxkudla.reserve.presenter.history.history_client.common.HistoryClientDetailFragment;
import com.maxkudla.reserve.presenter.history.history_service.HistoryServiceFragment;
import com.maxkudla.reserve.presenter.history.history_service.common.HistoryServiceDetailFragment;

import javax.inject.Inject;

public class HistoryActivity extends BaseActivity implements HasComponent<ActivityComponent>, HistoryRouter, HistoryContract.View {

    private ActivityComponent mActivityComponent;

    @Inject
    HistoryPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Injector.getComponent(this, ActivityComponent.class).inject(this);
        mPresenter.attachView(this);
        mPresenter.launchFragments();

    }

    @Override
    public ActivityComponent getComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent
                    .builder()
                    .appComponent(App.getAppComponent())
                    .build();
        }
        return mActivityComponent;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void launchToFragment(int type) {
        if (type == Constants.USER_SERVICE) {
            addFragment(R.id.history_container, HistoryServiceFragment.newInstance());
        } else if (type == Constants.USER_CLIENT) {
            addFragment(R.id.history_container, HistoryClientFragment.newInstance());
        }
    }

    @Override
    public void clientFragment(ReserveClient client) {
        HistoryClientDetailFragment fragment = HistoryClientDetailFragment.newInstance(null);
        fragment.setReserveClientClientRequest(client);
        addFragmentWithBackStack(R.id.history_detail_container, fragment);
    }

    @Override
    public void serviceFragment(ReserveService service) {
        HistoryServiceDetailFragment fragment = HistoryServiceDetailFragment.newInstance(null);
        fragment.setReserveServiceRequest(service);
        addFragmentWithBackStack(R.id.history_container, fragment);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetachView();
        super.onDestroy();
    }

    public void resolveToolbar(@NonNull BaseFragment fragment, String title) {
        Toolbar toolbar = fragment.getToolbar();
        if (toolbar == null) {
            return;
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        if (fragment.toolbarNavigationActive()) {
            ImageButton ibBack = (ImageButton) toolbar.findViewById(R.id.ibBack);
            ibBack.setOnClickListener(view -> onBackPressed());
        }
    }

    public void resolveToolbar(@NonNull Toolbar toolbar, String title) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        ImageButton ibBack = (ImageButton) toolbar.findViewById(R.id.ibBack);
        ibBack.setOnClickListener(view -> onBackPressed());

    }

}
