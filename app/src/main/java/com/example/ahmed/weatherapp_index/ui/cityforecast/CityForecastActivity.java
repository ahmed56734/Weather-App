package com.example.ahmed.weatherapp_index.ui.cityforecast;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ahmed.weatherapp_index.Injection;
import com.example.ahmed.weatherapp_index.R;
import com.example.ahmed.weatherapp_index.data.models.Forecast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityForecastActivity extends AppCompatActivity implements CityForecastContract.View {

    private static final String TAG = "CityForecastActivity";

    @BindView(R.id.rv_city_forecast)
    RecyclerView forecastRecyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.error_message)
    TextView errorMessageTextView;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;


    private ProgressBar actionBarProgressBar;


    private ForecastAdapter forecastAdapter;
    private CityForecastContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_forecast);
        ButterKnife.bind(this);

        setUpToolbar();

        presenter = CityForecastPresenter.getInstance(Injection.provideModelLayer(getApplicationContext()), this);

        forecastAdapter = new ForecastAdapter();
        forecastRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        forecastRecyclerView.setAdapter(forecastAdapter);

        showProgressBar();

        //parse intent
        Bundle bundle = getIntent().getExtras();
        presenter.parseIntentBundle(bundle);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshListener());


    }

    private void setUpToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customBar = inflater.inflate(R.layout.forecast_custom_bar, null);
        actionBarProgressBar = customBar.findViewById(R.id.action_bar_progress_bar);
        actionBar.setCustomView(customBar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateUi();
    }

    private void updateUi() {

        presenter.getForecast(new CityForecastContract.UpdateForecastCallback() {
            @Override
            public void onForecastLoaded(String cityName, List<Forecast> forecastList) {
                updateForecast(cityName, forecastList);
            }

            @Override
            public void onDataNotAvailable() {
                showErrorMessage();
            }
        });
    }

    @Override
    public void updateForecast(String cityName, List<Forecast> forecastList) {

        Log.d(TAG, "updateForecast");

        hideProgressBar();
        hideActionBarProgressBar();
        hideRefreshing();
        forecastAdapter.updateData(cityName, forecastList);

    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        presenter.parseIntentBundle(intent.getExtras());
        updateUi();
    }


    private void hideRefreshing() {
        swipeRefreshLayout.setRefreshing(false);
    }

    public void showActionBarProgressBar() {
        actionBarProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideActionBarProgressBar() {
        actionBarProgressBar.setVisibility(View.INVISIBLE);
    }

    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }


    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }


    public void showErrorMessage() {

        errorMessageTextView.setVisibility(View.VISIBLE);
        hideProgressBar();
        hideActionBarProgressBar();
    }

    void hideErrorMessage() {
        errorMessageTextView.setVisibility(View.GONE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    class SwipeRefreshListener implements SwipeRefreshLayout.OnRefreshListener {

        @Override
        public void onRefresh() {
            updateUi();
        }
    }


}
