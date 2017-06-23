package com.maxkudla.reserve.presenter.splash;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.view.View;

import com.maxkudla.reserve.App;
import com.maxkudla.reserve.Constants;
import com.maxkudla.reserve.R;
import com.maxkudla.reserve.di.components.DaggerActivityComponent;
import com.maxkudla.reserve.presenter.base.BaseActivity;
import com.maxkudla.reserve.presenter.login.LoginActivity;
import com.maxkudla.reserve.presenter.main.MainActivity;
import com.maxkudla.reserve.presenter.socket.SocketActivity;

import javax.inject.Inject;

import butterknife.BindView;


public class SplashActivity extends BaseActivity implements SplashContract.View, SplashRouter {

    @Inject
    SplashPresenter splashPresenter;
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();

    @BindView(R.id.fullscreen_content)
    View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };


    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash);
        super.onCreate(savedInstanceState);
        DaggerActivityComponent
                .builder()
                .appComponent(App.getAppComponent())
                .build()
                .inject(this);
        splashPresenter.setView(this);
        splashPresenter.setRouter(this);

    }

    @Override
    protected void onDestroy() {
        splashPresenter.onDetachView();
        super.onDestroy();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        delayedHide(100);
        splashPresenter.launchToNextScreen();
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    @Override
    public void launchToMainScreen() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void launchToSocketScreen(String serviceId, int type) {
        Intent intent = new Intent(this, SocketActivity.class);
        intent.putExtra(Constants.USER_TYPE, type);
        intent.putExtra(Constants.SERVICE_ID, serviceId);
        startActivity(intent);
        finish();
    }

    @Override
    public void launchToSocketScreen(int type, String status) {
        Intent intent = new Intent(this, SocketActivity.class);
        intent.putExtra(Constants.USER_TYPE, type);
        intent.putExtra(Constants.STATUS, status);
        startActivity(intent);
        finish();
    }

    @Override
    public void launchToLoginScreen() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
