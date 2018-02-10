package com.example.ahmed.weatherapp_index.ui.cities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ahmed.weatherapp_index.Injection;
import com.example.ahmed.weatherapp_index.R;
import com.example.ahmed.weatherapp_index.data.models.UserCity;
import com.example.ahmed.weatherapp_index.ui.cityforecast.CityForecastActivity;
import com.example.ahmed.weatherapp_index.utils.Utils;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements CitiesAdapter.CityOnClickListener {

    private static final String TAG = "MainActivity";

    private static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private static final int REQUEST_ACCESS_FINE_LOCATION_FOR_CURRENT_PLACE = 12;
    private CitiesPresenter mPresenter;

    public static final String CITY_NAME_KEY = "cityname";
    public static final String CITY_ID_KEY = "cityid";
    public static final String CITY_LAT_LNG_KEY = "latlng";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_cities)
    RecyclerView mRecyclerView;

    private FusedLocationProviderClient mFusedLocationClient;
    private GeoDataClient mGeoDataClient;
    private PlaceDetectionClient mPlaceDetectionClient;


    private CitiesAdapter mCitiesAdapter;
    private CitiesPresenter mCitiesPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        mPresenter = CitiesPresenter.getInstance(Injection.provideModelLayer(getApplicationContext()));
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        // Construct a GeoDataClient.
        mGeoDataClient = Places.getGeoDataClient(this, null);

        mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);


        mCitiesPresenter = CitiesPresenter.getInstance(Injection.provideModelLayer(getApplicationContext()));

        initRecyclerView();


    }

    private void initRecyclerView() {
        mCitiesAdapter = new CitiesAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mCitiesAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, linearLayoutManager.getOrientation()));
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new CityItemTouchCallback());
        itemTouchHelper.attachToRecyclerView(mRecyclerView);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_cities, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.search_cities:
                startCitiesSearch();
                return true;

            case R.id.update_location:
                getCurrentPlace();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateUi();

    }

    void updateUi() {

        mPresenter.getCities(new CitiesContract.CitiesResultCallback() {
            @Override
            public void onDataLoaded(List<UserCity> result) {
                updateAdapter(result);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    void updateAdapter(List<UserCity> result) {

        mCitiesAdapter.updateData(result);
    }


    private void getCurrentPlace() {

        if (!Utils.isNetworkAvailable(this)) {
            Toast.makeText(this, getString(R.string.offline_message), Toast.LENGTH_SHORT).show();
            return;
        }

        if (!checkLocationPermission()) {
            Log.i(TAG, "permesions not granted");
            return;
        }

        mPresenter.getCurrentCityFromGps(mPlaceDetectionClient, new CitiesContract.AddCityCallback() {
            @Override
            public void onCityAdded() {
                updateUi();
            }
            @Override
            public void onError() {
            }
        });


    }

    private boolean checkLocationPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED)
            return true;

        else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_ACCESS_FINE_LOCATION_FOR_CURRENT_PLACE);

            return false;
        }

    }


    private void startCitiesSearch() {
        try {
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                    .setFilter(new AutocompleteFilter.Builder()
                            .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                            .setTypeFilter(AutocompleteFilter.TYPE_FILTER_REGIONS)
                            .build())
                    .build(this);

            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                UserCity userCity = new UserCity(place.getId(), place.getAddress().toString(), place.getLatLng().latitude, place.getLatLng().longitude, null);
                Log.i(TAG, "Place: " + place.getAddress() + " id " + place.getId() + " "+place.getLatLng().latitude +" "+place.getLatLng().longitude);
                mPresenter.addCity(userCity, new CitiesContract.AddCityCallback() {
                    @Override
                    public void onCityAdded() {
                        updateUi();
                    }
                    @Override
                    public void onError() {
                    }
                });


            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                Log.e(TAG, status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_ACCESS_FINE_LOCATION_FOR_CURRENT_PLACE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getCurrentPlace();
                }

            }
        }
    }

    @Override
    public void onClick(UserCity city) {
        openForecastActivity(city);
    }

    private void openForecastActivity(UserCity city) {

        Intent intent = new Intent(MainActivity.this, CityForecastActivity.class);
        intent.putExtra(CITY_NAME_KEY, city.getName());
        intent.putExtra(CITY_ID_KEY, city.getPlaceId());
        intent.putExtra(CITY_LAT_LNG_KEY, new LatLng(city.getLatitude(), city.getLongitude()));
        startActivity(intent);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    class CityItemTouchCallback extends ItemTouchHelper.SimpleCallback {


        public CityItemTouchCallback() {
            super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int position = viewHolder.getAdapterPosition();
            if (position == 0)
                return 0;
            return super.getSwipeDirs(recyclerView, viewHolder);
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            int position = viewHolder.getAdapterPosition();
            if (position != 0) {

                mCitiesPresenter.deleteCity(mCitiesAdapter.getCity(position));
                mCitiesAdapter.deleteCity(position);
            }

        }
    }
}

