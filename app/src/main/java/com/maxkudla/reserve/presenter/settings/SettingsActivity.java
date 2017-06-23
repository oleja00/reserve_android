package com.maxkudla.reserve.presenter.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.maxkudla.reserve.App;
import com.maxkudla.reserve.R;
import com.maxkudla.reserve.di.common.HasComponent;
import com.maxkudla.reserve.di.components.ActivityComponent;
import com.maxkudla.reserve.di.components.DaggerActivityComponent;
import com.maxkudla.reserve.presenter.base.BaseActivity;
import com.maxkudla.reserve.presenter.settings.settings_details.SettingsFragment;

public class SettingsActivity extends BaseActivity implements HasComponent<ActivityComponent> {

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        showSettings();

    }

    private void showSettings() {
        addFragment(R.id.settings_container, SettingsFragment.newInstance());
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
}
