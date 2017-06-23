package com.maxkudla.reserve.presenter.main.map;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.maxkudla.reserve.Constants;
import com.maxkudla.reserve.R;
import com.maxkudla.reserve.di.common.Injector;
import com.maxkudla.reserve.di.components.ActivityComponent;
import com.maxkudla.reserve.presenter.base.BaseFragment;
import com.maxkudla.reserve.presenter.base.BasePresenter;
import com.maxkudla.reserve.presenter.common.adapters.AutoCompleteAdapter;
import com.maxkudla.reserve.presenter.main.MainActivity;
import com.maxkudla.reserve.utils.GeoCoderIntentService;

import java.util.ArrayList;

import javax.inject.Inject;


/**
 * Created by Developer on 02.05.2017.
 */

public class MapSearchFragment extends BaseFragment implements MapSearchContract.View, OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener, AutoCompleteAdapter.PlaceAutoCompleteInterface {

    @Inject
    MapSearchPresenter mMapSearchPresenter;


    private static final int REQUEST_CHECK_SETTINGS = 2;

    private static final LatLngBounds BOUNDS_UKRAINE = new LatLngBounds(
            new LatLng(21.5300, 52.3000), new LatLng(40.3300, 44.1100));


    private GoogleMap mMap;
    private Button mSelectButton;
    private TextView mPrimaryAddress, mSecondaryAddress;
    private LinearLayout mAddressLayout;
    private ImageButton mCurrentLocation;
    private Location mLocation;
    private LocationRequest mLocationRequest;

    protected GoogleApiClient mGoogleApiClient;

    private Double mLatitude, mLongitude;

    private EditText mSearchText;
    private RecyclerView mRecyclerView;
    private AutoCompleteAdapter mAdapter;
    private ImageView mCustomMarker;

    private static boolean sCameraMoved = true;

    private AddressResultReceiver mAddressResultReceiver;

    private GoogleMap map;

    private MapFragment mapFragment;
    private View view;

    public static MapSearchFragment newInstance() {
        MapSearchFragment fragment = new MapSearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        try {
            if(((MainActivity)getActivity()).getMapView()==null){
                view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_map_search, null);
                ((MainActivity)getActivity()).setMapView(view);
            }else {
                view = ((MainActivity)getActivity()).getMapView();
            }

        } catch (InflateException e) {
            Log.d("MapsTag", e.getMessage());
        }


        if (isGooglePlayServicesAvailable()) {
            buildGoogleAPiClient();
        } else {
            Log.e("MapTag", "Google Play Services not available");
        }
        createLocationRequest();

        mSelectButton = (Button) view.findViewById(R.id.select_button);
        mPrimaryAddress = (TextView) view.findViewById(R.id.firstAddress);
        mSecondaryAddress = (TextView) view.findViewById(R.id.secondAddress);
        mAddressLayout = (LinearLayout) view.findViewById(R.id.address_layout);

        mCurrentLocation = (ImageButton) view.findViewById(R.id.get_current_location);
        mCustomMarker = (ImageView) view.findViewById(R.id.map_custom_marker);

        mSearchText = (EditText) view.findViewById(R.id.search_box);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mapFragment = ((MapFragment) getActivity().getFragmentManager()
                .findFragmentById(R.id.map_map));
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.getComponent(getActivity(), ActivityComponent.class).inject(this);
        if (getArguments() != null) {

        }
        mMapSearchPresenter.setView(this);
        mMapSearchPresenter.setRouter((MainActivity) getActivity());
    }

    @Override
    protected BasePresenter getPresenter() {
        return mMapSearchPresenter;
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

        isGpsOn();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new AutoCompleteAdapter(getContext(), R.layout.layout_recommendation, mGoogleApiClient, BOUNDS_UKRAINE, null, this);
        mRecyclerView.setAdapter(mAdapter);


        mAddressResultReceiver = new AddressResultReceiver(null);

        mSearchText.setOnTouchListener((v, event) -> {
            mSearchText.setCursorVisible(true);
            return false;
        });

        mSearchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.equals("") && mGoogleApiClient.isConnected()) {
                    mAdapter.getFilter().filter(s.toString());
                } else if (!mGoogleApiClient.isConnected()) {
                    Log.e("MapTag", "API  NOT CONNECTED");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.getFilter().filter(null);
                        }
                    }, 500);
                }
            }
        });

        mCurrentLocation.setOnClickListener(v -> isGpsOn());

        mSelectButton.setOnClickListener(v -> {
            //TODO: Sending info about coordinates
            //mLongitude & mLongitude
//                mLatitude = mMap.getCameraPosition().target.latitude;
//                mLongitude = mMap.getCameraPosition().target.longitude;
            Log.d("MapTag", mLatitude + "; " + mLongitude);
//            mLatitude = 48.51660399999999;
//            mLongitude = 35.04638699999998;

//            mLatitude = 48.453254699707;
//            mLongitude = 35.063919067383;
            mMapSearchPresenter.confirmLocation(mLatitude, mLongitude);
        });


    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY | LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
    }

    // Function to build the Google Api Client..
    protected synchronized void buildGoogleAPiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .build();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(48.379433, 31.16558), 4.0f));
        loadMap();

//        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
//            @Override
//            public void onCameraIdle() {
//                Log.d("CameraTag ", "idle");
//            }
//        });
    }


    @Override
    public void onPlaceClick(ArrayList<AutoCompleteAdapter.PlaceAutoComplete> mResultList, int position) {
        if (mResultList != null) {
            try {
                final String placeId = String.valueOf(mResultList.get(position).getPlaceId());

                mSearchText.setText("");
                mAdapter.clearList();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mSearchText.getWindowToken(), 0);
                mSearchText.setCursorVisible(false);
                PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi.getPlaceById(mGoogleApiClient, placeId);
                placeResult.setResultCallback(new ResultCallback<PlaceBuffer>() {
                    @Override
                    public void onResult(@NonNull PlaceBuffer places) {
                        if (!places.getStatus().isSuccess()) {
                            // Request did not complete successfully
                            Log.e("MapTag", "Place query did not complete. Error: " + places.getStatus().toString());
                            places.release();
                            return;
                        }
                        Place place = places.get(0);
                        sCameraMoved = false;
                        mLatitude = place.getLatLng().latitude;
                        mLongitude = place.getLatLng().longitude;
                        mPrimaryAddress.setText(place.getName());
                        mSecondaryAddress.setText(place.getAddress());
                        mAddressLayout.setVisibility(View.VISIBLE);
                        loadMap();
                        places.release();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("MapTag", e.toString());
            }
        }
    }


    /**
     * Method to display the location on Map
     */
    public void loadMap() {
        int cameraZoom = 17;
        if (mLatitude == null && mLongitude == null) {
            mLatitude = 48.379433;
            mLongitude = 31.16558;
            cameraZoom = 4;
        }
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(mLatitude, mLongitude))      // Sets the center of the map to location user
                .zoom(cameraZoom)                   // Sets the zoom// Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        mMap.getUiSettings().setCompassEnabled(false);
        mMap.getUiSettings().setIndoorLevelPickerEnabled(false);

        mMap.setOnCameraIdleListener(() -> {
            Location location = new Location("");
            if (sCameraMoved) {
                mLatitude = mMap.getCameraPosition().target.latitude;
                mLongitude = mMap.getCameraPosition().target.longitude;
                location.setLatitude(mLatitude);
                location.setLongitude(mLongitude);
                convertLocationToAddress(location);
            }
            sCameraMoved = true;
        });
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        startLocationUpdates();
    }


    protected void startLocationUpdates() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
            mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);


        } else {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
            mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        }
    }

    /**
     * Method to stop the regular location updates
     */
    protected void stopLocationUpdates() {
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(
                    mGoogleApiClient, this);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        mapFragment.onStop();
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapFragment.onResume();
        if (mGoogleApiClient.isConnected()) {
            startLocationUpdates();
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        mapFragment.onPause();
        stopLocationUpdates();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        mapFragment.onLowMemory();
        super.onLowMemory();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.e("MapTag", "Connection Suspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e("MapTag", "Connection Failed");
    }

    @Override
    public void onLocationChanged(Location location) {
        mLocation = location;
    }


    /**
     * Method to get current location and do reverse geocoding of the location
     */
    private void getCurrentLocationAddress() {
        if (mLocation != null) {
            mLatitude = mLocation.getLatitude();
            mLongitude = mLocation.getLongitude();
            convertLocationToAddress(mLocation);
            loadMap();
        }
    }

    /**
     * Method to check if google play services are enabled or not
     *
     * @return boolean status
     */
    public boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(getContext());
        if (status != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(status)) {
                googleApiAvailability.getErrorDialog(getActivity(), status, 2404).show();
            }
            return false;
        }
        return true;
    }

    /**
     * Method to check if GPS is on or not
     */
    private void isGpsOn() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
                final Status status = locationSettingsResult.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        getCurrentLocationAddress();
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            status.startResolutionForResult(
                                    getActivity(),
                                    REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            Log.e("MapTag", "Exception : " + e);
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.e("MapTag", "Location settings are not satisfied.");
                        break;
                }
            }
        });

    }

    /**
     * Method to convert latitude and longitude to  address via reverse-geocoding
     */
    private void convertLocationToAddress(Location location) {
        Intent intent = new Intent(getActivity(), GeoCoderIntentService.class);
        intent.putExtra(Constants.RECEIVER, mAddressResultReceiver);
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, location);
        getActivity().startService(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        if (mLocation != null) {
                            getCurrentLocationAddress();
                        } else {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    getCurrentLocationAddress();
                                }
                            }, 2000);
                        }
                        break;
                    case Activity.RESULT_CANCELED:
                        break;
                    default:
                        break;
                }
                break;
        }
    }


    private class AddressResultReceiver extends ResultReceiver {

        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        protected void onReceiveResult(final int resultCode, Bundle resultData) {

            final String address1 = resultData.getString(Constants.ADDRESS_DATA_KEY1);
            final String address2 = resultData.getString(Constants.ADDRESS_DATA_KEY2);
            if(getActivity()!= null){
                getActivity().runOnUiThread(() -> {
                    if (resultCode == Constants.SUCCESS_RESULT) {
                        mPrimaryAddress.setText(address1);
                        mSecondaryAddress.setText(address2);
                        mAddressLayout.setVisibility(View.VISIBLE);
                        Log.d("AddressTag", address1 + "; " + address2);
                    } else {
                        Log.e("ErrorTag", "Error while fetching data");
                        mLatitude = mLongitude = null;
                        mPrimaryAddress.setText(address1);
                        mSecondaryAddress.setText("");
                        Log.d("AddressTag", address1 + "; " + address2);
                    }
                });
            }

        }
    }
}
