package com.maxkudla.reserve.presenter.common.drawer;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.maxkudla.reserve.di.common.Injector;
import com.maxkudla.reserve.di.components.ActivityComponent;
import com.maxkudla.reserve.models.menu.MenuItem;

import java.util.List;

import javax.inject.Inject;

public class RecyclerDrawer extends RecyclerView implements RecyclerDrawerContract.View {

    @Inject
    RecyclerDrawerPresenter mPresenter;

    RecyclerDrawerAdapter mRecyclerDrawerAdapter;
    public RecyclerDrawer(Context context) {
        super(context);
        init();
    }

    public RecyclerDrawer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RecyclerDrawer(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    public void updateAdapter(List<MenuItem> items) {
        setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerDrawerAdapter = new RecyclerDrawerAdapter(items, position -> mPresenter.setSelectedMenuItem(position));
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
//        mPresenter.setRouter((MainActivity)getContext());
        mPresenter.getItemsMenu();
    }

}
