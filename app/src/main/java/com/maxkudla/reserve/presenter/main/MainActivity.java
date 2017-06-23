package com.maxkudla.reserve.presenter.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.maxkudla.reserve.App;
import com.maxkudla.reserve.R;
import com.maxkudla.reserve.di.common.HasComponent;
import com.maxkudla.reserve.di.common.Injector;
import com.maxkudla.reserve.di.components.ActivityComponent;
import com.maxkudla.reserve.di.components.DaggerActivityComponent;
import com.maxkudla.reserve.models.options.Option;
import com.maxkudla.reserve.presenter.base.BaseActivity;
import com.maxkudla.reserve.presenter.history.HistoryActivity;
import com.maxkudla.reserve.presenter.login.LoginActivity;
import com.maxkudla.reserve.presenter.main.categories.CategoriesFragment;
import com.maxkudla.reserve.presenter.main.map.MapSearchFragment;
import com.maxkudla.reserve.presenter.main.options.OptionsFragment;
import com.maxkudla.reserve.presenter.main.options.common.OptionsAdapter;
import com.maxkudla.reserve.presenter.main.parametrs.ParametrsFragment;
import com.maxkudla.reserve.presenter.settings.SettingsActivity;
import com.maxkudla.reserve.presenter.socket.SocketActivity;
import com.maxkudla.reserve.presenter.socket.book.common.GalleryFragment;
import com.maxkudla.reserve.utils.PermissionsListener;
import com.maxkudla.reserve.utils.PermissionsManager;

import java.util.List;

import javax.inject.Inject;

import io.intercom.android.sdk.Intercom;

import static com.maxkudla.reserve.Constants.QUERY_ID;
import static com.maxkudla.reserve.Constants.USER_CLIENT;
import static com.maxkudla.reserve.Constants.USER_TYPE;

public class MainActivity extends BaseActivity
        implements
//        NavigationView.OnNavigationItemSelectedListener        ,
        MainRouter
        , PermissionsListener
        , HasComponent<ActivityComponent>
        , MainContract.View {

    private ActivityComponent mActivityComponent;

    private DrawerLayout drawer;
    private final int REQUEST_CODE_PLACEPICKER = 1;
    private PermissionsManager permissionsManager;
    private View mapView;

    @Inject
    MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Injector.getComponent(this, ActivityComponent.class).inject(this);
        mPresenter.attachView(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

        addFragment(R.id.container, CategoriesFragment.newInstance());
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public void closeDrawer() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main2, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
//            drawer.openDrawer(GravityCompat.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//            socketActivity();
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }
//
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }

    @Override
    public void showCategoriesFragment() {
        replaceFragmentWithBackStack(R.id.container, CategoriesFragment.newInstance());
    }

    @Override
    public void showOptionsFragment(String category) {
        replaceFragmentWithBackStack(R.id.container, OptionsFragment.newInstance(category));
    }

    @Override
    public void showImageGallery(String id){
        replaceFragmentWithBackStack(R.id.container, GalleryFragment.newInstance(id));
    }

    @Override
    public void showOptionListFragment(Option optionList, OptionsAdapter.OnOptionsChangeListenerListener listener){
        ParametrsFragment parametrsFragment = ParametrsFragment.newInstance();
        parametrsFragment.setOption(optionList);
        parametrsFragment.setOnOptionsItemChangeListener(listener);
        addFragmentWithBackStack(R.id.container, parametrsFragment);
    }



    @Override
    public void showMapFragment() {

        permissionsManager = new PermissionsManager(this);
        if (!PermissionsManager.areLocationPermissionsGranted(this)) {
            permissionsManager.requestLocationPermissions(this);
        } else {
            replaceFragmentWithBackStack(R.id.container, MapSearchFragment.newInstance());
        }

    }

    public void moveToMap() {
        PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
        // this would only work if you have your Google Places API working

        try {
            Intent intent = intentBuilder.build(this);
            startActivityForResult(intent, REQUEST_CODE_PLACEPICKER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PLACEPICKER && resultCode == RESULT_OK) {
            Place placeSelected = PlacePicker.getPlace(data, this);
            Log.d("MapTag", placeSelected.getLatLng().longitude + ": " + placeSelected.getLatLng().latitude);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(this, "This app needs location permissions in order to show its functionality.",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            moveToMap();
        } else {
            Toast.makeText(this, "You didn't grant location permissions.",
                    Toast.LENGTH_LONG).show();
            finish();
        }
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
    public void socketActivity(String queryId){
        Intent intent = new Intent(this, SocketActivity.class);
        intent.putExtra(USER_TYPE, USER_CLIENT);
        intent.putExtra(QUERY_ID, queryId);
        startActivity(intent);
        finish();
    }
   @Override
    public void historyActivity(){
        startActivity(new Intent(this, HistoryActivity.class));
    }

    @Override
    public void settingsActivity(){
        startActivity(new Intent(this, SettingsActivity.class));
    }

    @Override
    public void logout() {
        Intercom.client().reset();
        App.getAuthTokenHolder().setToken(null);
//        Digits.logout();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void shareApp() {
        Intent i = new Intent(android.content.Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(android.content.Intent.EXTRA_TEXT,
                " https://play.google.com/store/apps/details?id=com.maxkudla.reserve");
        startActivity(Intent.createChooser(i, "SHARE"));
    }

    @Override
    public void intercomChat() {
        Intercom.client().displayMessenger();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetachView();
        super.onDestroy();
    }

    public void setMapView(View v) {
        mapView = v;
    }

    public View getMapView(){
        return mapView;
    }
}
