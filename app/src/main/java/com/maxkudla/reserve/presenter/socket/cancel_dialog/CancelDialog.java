package com.maxkudla.reserve.presenter.socket.cancel_dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maxkudla.reserve.R;
import com.maxkudla.reserve.di.common.Injector;
import com.maxkudla.reserve.di.components.ActivityComponent;
import com.maxkudla.reserve.presenter.socket.SocketActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Oleja on 06.06.2017.
 */

public class CancelDialog extends DialogFragment implements CancelContract.View {

    @Inject
    CancelPresenter mPresenter;

    CancelOkListener mCancelOkListener;

    public static CancelDialog newInstance() {
        return new CancelDialog();
    }

    public void setmCancelOkListener(CancelOkListener cancelOkListener){
        mCancelOkListener = cancelOkListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.getComponent(getActivity(), ActivityComponent.class).inject(this);
        setStyle(STYLE_NO_TITLE, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_cancle, container, false);
        ButterKnife.bind(this, view);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        mPresenter.setView(this);
        mPresenter.setRouter((SocketActivity) getActivity());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @OnClick(R.id.tvCancel)
    void onCancelClick(){
        dismiss();
    }

    @OnClick(R.id.tvOk)
    void onOkClick(){
        mPresenter.onCancelAccept(mCancelOkListener);
    }
}
