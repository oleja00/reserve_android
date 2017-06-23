package com.maxkudla.reserve.presenter.settings.settings_details;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maxkudla.reserve.App;
import com.maxkudla.reserve.R;
import com.maxkudla.reserve.di.common.Injector;
import com.maxkudla.reserve.di.components.ActivityComponent;
import com.maxkudla.reserve.presenter.base.BaseFragment;
import com.maxkudla.reserve.presenter.base.BasePresenter;

import javax.inject.Inject;

import butterknife.BindView;

public class SettingsFragment extends BaseFragment implements SettingsContract.View {

    @Inject
    SettingsPresenter mPresenter;

    @BindView(R.id.tvVersion)
    TextView tvVersion;

    @BindView(R.id.tvPhoneNumber)
    TextView tvPhone;

    @BindView(R.id.tvRate)
    TextView tvRate;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.getComponent(getActivity(), ActivityComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        mPresenter.setView(this);
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
        tvPhone.setText(App.getAuthTokenHolder().getPhone());
        try {
            tvVersion.setText(getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {

        }

        tvRate.setOnClickListener(v ->
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(
                        "https://play.google.com/store/apps/details?id=" + getActivity().getPackageName()))));
    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

}
