package com.maxkudla.reserve.presenter.common.socket_drawer;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.maxkudla.reserve.di.common.Injector;
import com.maxkudla.reserve.di.components.ActivityComponent;
import com.maxkudla.reserve.models.menu.MenuItem;
import com.maxkudla.reserve.presenter.socket.SocketActivity;

import java.util.List;

import javax.inject.Inject;

public class RecyclerDrawerSocket extends RecyclerView implements RecyclerDrawerSocketContract.View {

    @Inject
    RecyclerDrawerSocketPresenter mPresenter;

    RecyclerDrawerSocketAdapter mRecyclerDrawerAdapter;
    public RecyclerDrawerSocket(Context context) {
        super(context);
        init();
    }

    public RecyclerDrawerSocket(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RecyclerDrawerSocket(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    public void updateAdapter(List<MenuItem> items) {
        setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerDrawerAdapter = new RecyclerDrawerSocketAdapter(items, position -> mPresenter.setSelectedMenuItem(position));
        setAdapter(mRecyclerDrawerAdapter);
    }

    @Override
    public void updateSelectedPositionAdapter(MenuItem item) {
        mRecyclerDrawerAdapter.updateView(item);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

    }

    @Override
    protected void onDetachedFromWindow() {
        mPresenter.dettachView();
        super.onDetachedFromWindow();
    }

    private void init() {
        Injector.getComponent(getContext(), ActivityComponent.class).inject(this);
        mPresenter.setView(this);
        mPresenter.setRouter((SocketActivity) getContext());
        mPresenter.getItemsMenu();
    }

}
