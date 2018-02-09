package com.example.ahmed.weatherapp_index.ui.cityforecast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.ahmed.weatherapp_index.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityForecastActivity extends AppCompatActivity {

    @BindView(R.id.rv_city_forecast)
    RecyclerView mForecastRecyclerView;



    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_forecast);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

    }
}
