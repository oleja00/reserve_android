package com.maxkudla.reserve.presenter.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;


/**
 * Created by Developer on 19.04.2017.
 *
 */

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

    }

    public void resolveToolbar(@NonNull BaseFragment fragment) {
        Toolbar toolbar = fragment.getToolbar();
        if (toolbar == null) {
            return;
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        if (fragment.toolbarNavigationActive()) {
            /*ImageButton backButton = (ImageButton) toolbar.findViewById(R.id.icn_back);
            backButton.setOnClickListener(e -> {
                onBackPressed();
            });*/
        }
    }

    public void replaceFragment(int viewGroupId, @NonNull BaseFragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(viewGroupId, fragment)
                .commit();
    }

    public void addFragmentWithBackStack(int viewGroupId, @NonNull BaseFragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(viewGroupId, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    public void addFragmentWithBackStack(int viewGroupId, @NonNull Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(viewGroupId, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    public void addFragment(int viewGroupId, @NonNull BaseFragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(viewGroupId, fragment, fragment.getClass().getSimpleName())
                .commit();
    }

    public void replaceFragmentWithBackStack(int viewGroupId, @NonNull BaseFragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(viewGroupId, fragment)
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }


}
