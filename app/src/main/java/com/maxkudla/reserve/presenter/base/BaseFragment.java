package com.maxkudla.reserve.presenter.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Developer on 19.04.2017.
 */

public abstract class BaseFragment extends Fragment implements BaseView {

    private Unbinder unbinder;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
    }

    @Override
    public void onDestroy() {
        getPresenter().onDetachView();
        if (unbinder != null){
            unbinder.unbind();
        }
        super.onDestroy();
    }

    public abstract Toolbar getToolbar();

    public abstract boolean toolbarNavigationActive();

    protected abstract void initView();

    protected abstract BasePresenter getPresenter();


    @Override
    public void onNetworkFailure() {
        //showAlertDialog(getString(R.string.network_failure));
    }

    @Override
    public void onRequestFailure() {
        //showAlertDialog(getString(R.string.request_error));
    }

    protected void showAlertDialog(String message) {
        new AlertDialog.Builder(getContext())
                .setMessage(message)
                /*.setPositiveButton(
                        getString(R.string.ok),
                        (DialogInterface dialog, int which) -> {
                        })*/
                .setCancelable(false)
                .show();
    }
}
