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
    RecyclerView mForecastRecyclerView;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.error_message)
    TextView mErrorMessageTextView;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;


    private ProgressBar mActionBarProgressBar;


    private ForecastAdapter mForecastAdapter;
    private CityForecastPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_forecast);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customBar = inflater.inflate(R.layout.forecast_custom_bar, null);
        mActionBarProgressBar = customBar.findViewById(R.id.action_bar_progress_bar);
        actionBar.setCustomView(customBar);

        mPresenter = CityForecastPresenter.getInstance(Injection.provideModelLayer(getApplicationContext()), this);

        mForecastAdapter = new ForecastAdapter();
        mForecastRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mForecastRecyclerView.setAdapter(mForecastAdapter);

        showProgressBar();

        //parse intent
        Bundle bundle = getIntent().getExtras();
        mPresenter.parseIntentBundle(bundle);


        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshListener());




    }

    @Override
    protected void onStart() {
        super.onStart();
        updateUi();
    }

    private void updateUi() {

        mPresenter.getForecast(new CityForecastContract.UpdateForecastCallback() {
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
        mForecastAdapter.updateData(cityName, forecastList);

    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        mPresenter.parseIntentBundle(intent.getExtras());
        updateUi();
    }


    private void hideRefreshing() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public void showActionBarProgressBar() {
        mActionBarProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideActionBarProgressBar() {
        mActionBarProgressBar.setVisibility(View.INVISIBLE);
    }

    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }


    public void hideProgressBar() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }


    public void showErrorMessage() {

        mErrorMessageTextView.setVisibility(View.VISIBLE);
        hideProgressBar();
        hideActionBarProgressBar();
    }

    void hideErrorMessage() {
        mErrorMessageTextView.setVisibility(View.GONE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    class SwipeRefreshListener implements SwipeRefreshLayout.OnRefreshListener{

        @Override
        public void onRefresh() {
            updateUi();
        }
    }


}
