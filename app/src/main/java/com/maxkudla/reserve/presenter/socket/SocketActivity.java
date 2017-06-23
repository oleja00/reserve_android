package com.maxkudla.reserve.presenter.socket;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;

import com.maxkudla.reserve.App;
import com.maxkudla.reserve.R;
import com.maxkudla.reserve.di.common.HasComponent;
import com.maxkudla.reserve.di.common.Injector;
import com.maxkudla.reserve.di.components.ActivityComponent;
import com.maxkudla.reserve.di.components.DaggerActivityComponent;
import com.maxkudla.reserve.models.client.ReserveClient;
import com.maxkudla.reserve.models.service.ReserveService;
import com.maxkudla.reserve.presenter.base.BaseActivity;
import com.maxkudla.reserve.presenter.base.BaseFragment;
import com.maxkudla.reserve.presenter.history.HistoryActivity;
import com.maxkudla.reserve.presenter.login.LoginActivity;
import com.maxkudla.reserve.presenter.main.MainActivity;
import com.maxkudla.reserve.presenter.settings.SettingsActivity;
import com.maxkudla.reserve.presenter.socket.book.BookATableFragment;
import com.maxkudla.reserve.presenter.socket.book.common.GalleryFragment;
import com.maxkudla.reserve.presenter.socket.cancel_dialog.CancelDialog;
import com.maxkudla.reserve.presenter.socket.cancel_dialog.CancelOkListener;
import com.maxkudla.reserve.presenter.socket.guest.GuestFragment;
import com.maxkudla.reserve.presenter.socket.service.ServiceFragment;
import com.maxkudla.reserve.presenter.socket.service.socket_service.common.SocketServiceAdapter;
import com.maxkudla.reserve.presenter.socket.socket_client.SocketClientFragment;
import com.maxkudla.reserve.presenter.socket.socket_client.common.SocketClientAdapter;

import javax.inject.Inject;

import static com.maxkudla.reserve.Constants.SERVICE_ID;
import static com.maxkudla.reserve.Constants.STATUS;
import static com.maxkudla.reserve.Constants.USER_CLIENT;
import static com.maxkudla.reserve.Constants.USER_TYPE;

public class SocketActivity extends BaseActivity implements SocketContract.View
        , SocketRouter
        , HasComponent<ActivityComponent> {
    ActivityComponent mActivityComponent;
    private DrawerLayout drawer;

    @Inject
    SocketPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        Injector.getComponent(this, ActivityComponent.class).inject(this);
        mPresenter.attachView(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mPresenter.showScocketFragment(getIntent().getIntExtra(USER_TYPE, USER_CLIENT), getIntent().getStringExtra(STATUS), getIntent().getStringExtra(SERVICE_ID));
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void showBookFragment(ReserveClient reserveClientClientRequest) {
        BookATableFragment bookATableFragment = BookATableFragment.newInstance("");
        bookATableFragment.setReserveClientClientRequest(reserveClientClientRequest);
        addFragmentWithBackStack(R.id.container, bookATableFragment);
    }

    @Override
    public void showBookFragment(ReserveClient reserveClientRequest, SocketClientAdapter.OnRequestSentToServer onRequestSentToServer) {
        BookATableFragment bookATableFragment = BookATableFragment.newInstance("");
        bookATableFragment.setReserveClientClientRequest(reserveClientRequest);
        bookATableFragment.setOnRequestSentToServer(onRequestSentToServer);
        addFragmentWithBackStack(R.id.container, bookATableFragment);
    }

    @Override
    public void showGuestFragment(ReserveService reserveServiceRequest, SocketServiceAdapter.OnRequestSentToClient onRequestSentToClient) {
        GuestFragment guestFragment = GuestFragment.newInstance("");
        guestFragment.setReserveServiceRequest(reserveServiceRequest);
        guestFragment.setOnRequestSentToClient(onRequestSentToClient);
        addFragmentWithBackStack(R.id.container, guestFragment);
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
    public void showImageGallery(String id) {
        replaceFragmentWithBackStack(R.id.container, GalleryFragment.newInstance(id));
    }

//    @Override
//    public void showHistory() {
//        addFragmentWithBackStack(R.id.container, ServiceFragment.newInstance());
//    }

    @Override
    public void showCancelDialog(CancelOkListener cancelOkListener) {
        CancelDialog cancelDialog = CancelDialog.newInstance();
        cancelDialog.setmCancelOkListener(cancelOkListener);
        cancelDialog.show(getSupportFragmentManager(), cancelDialog.getClass().getSimpleName());
    }

    @Override
    public void cancelRequest() {
        startActivity(new Intent(SocketActivity.this, MainActivity.class));
        finish();

    }

    @Override
    public void closeSocket() {
        startActivity(new Intent(SocketActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            BookATableFragment fragment = (BookATableFragment) getSupportFragmentManager().findFragmentByTag(BookATableFragment.class.getSimpleName());
            if (fragment != null) {
                if(!fragment.isReserved()){
                    super.onBackPressed();
                }else {
                    fragment.onCancelClick();
                }
            } else {
                SocketClientFragment socketClientFragment = (SocketClientFragment) getSupportFragmentManager().findFragmentByTag(SocketClientFragment.class.getSimpleName());
                if(socketClientFragment == null){
                    super.onBackPressed();
                }else {
                    CancelDialog cancelDialog = CancelDialog.newInstance();
                    cancelDialog.setmCancelOkListener(socketClientFragment);
                    cancelDialog.show(getSupportFragmentManager(), CancelDialog.class.getSimpleName());
                }
            }
        }
    }

    @Override
    public void showGuest(String sId, long lId) {
        addFragmentWithBackStack(R.id.container, GuestFragment.newInstance(sId));
    }

    @Override
    public void historyActivity() {
        startActivity(new Intent(this, HistoryActivity.class));
    }

    @Override
    public void logout() {
        App.getAuthTokenHolder().setToken(null);
//        Digits.logout();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }


    @Override
    public void showServiceFragment(String serviceId) {
        addFragment(R.id.container, ServiceFragment.newInstance(serviceId));
    }

    @Override
    public void showClientFragment() {
        addFragment(R.id.container, SocketClientFragment.newInstance());
    }

    @Override
    public void showClientFragment(String status) {
        addFragment(R.id.container, SocketClientFragment.newInstance(status));
    }

    public void resolveToolbar(@NonNull BaseFragment fragment, String title) {
        Toolbar toolbar = fragment.getToolbar();
        if (toolbar == null) {
            return;
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        if (fragment.toolbarNavigationActive()) {
            ImageButton backButton = (ImageButton) toolbar.findViewById(R.id.ibDrawer);
            backButton.setOnClickListener(e -> {
                drawer.openDrawer(GravityCompat.START);
            });
        }
    }

    public void openDrawerCallback() {
        drawer.openDrawer(GravityCompat.START);
    }

    @Override
    public void settingsActivity() {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    @Override
    public void closeDrawer() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }
}
