package com.example.ahmed.weatherapp_index.ui.cityforecast;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahmed.weatherapp_index.R;
import com.example.ahmed.weatherapp_index.data.models.Forecast;
import com.example.ahmed.weatherapp_index.data.models.Weather;
import com.example.ahmed.weatherapp_index.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ahmed on 2/9/18.
 */

public class ForecastAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "ForecastAdapter";

    private String cityName;
    private List<Forecast> forecastList;

    private static final int TODAY_FORECAST_VIEW_TYPE = 10;


    ForecastAdapter() {
        forecastList = new ArrayList<>();
    }

    public void updateData(String cityName, List<Forecast> forecast) {

        Log.d(TAG, "updateData");

        this.cityName = cityName;
//        this.forecastList.clear();
//        this.forecastList.addAll(forecast);
        this.forecastList = forecast;

        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0)
            return TODAY_FORECAST_VIEW_TYPE;

        return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView;

        if (viewType == TODAY_FORECAST_VIEW_TYPE) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_today_weather, parent, false);
            return new TodayWeatherViewHolder(itemView);
        } else {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_weather, parent, false);
            return new ForecastViewHolder(itemView);
        }


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Forecast forecast = forecastList.get(position);

        if (getItemViewType(position) == TODAY_FORECAST_VIEW_TYPE) {
            ((TodayWeatherViewHolder) holder).bindData(forecast);
        } else
            ((ForecastViewHolder) holder).bindData(forecast);

    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }


    class TodayWeatherViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.weather_icon)
        ImageView weatherIconImageView;
        @BindView(R.id.date)
        TextView dateTextView;
        @BindView(R.id.weather_description)
        TextView weatherDescriptionTextView;
        @BindView(R.id.city_name)
        TextView cityNameTextView;
        @BindView(R.id.temperature)
        TextView temperatureTextView;
        @BindView(R.id.wind)
        TextView windTextView;
        @BindView(R.id.pressure)
        TextView pressureTextView;
        @BindView(R.id.humidity)
        TextView humidityTextView;
        @BindView(R.id.last_update)
        TextView lastUpdateTextView;

        private Context context;

        public TodayWeatherViewHolder(View itemView) {
            super(itemView);

            context = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        void bindData(Forecast forecast) {

            Weather weather = forecast.getWeather().get(0);
            String iconUrl = Utils.getWeatherIconUrl(weather.getIcon());

            Picasso.with(context).load(iconUrl).into(weatherIconImageView);
            dateTextView.setText(forecast.getDate());
            weatherDescriptionTextView.setText(weather.getDescription());
            cityNameTextView.setText(cityName);

            temperatureTextView
                    .setText(context.getString(R.string.format_temperature, forecast.getMain().getTemp()));

            windTextView
                    .setText(String.format("%s %s",
                            context.getString(R.string.wind_label),
                            context.getString(R.string.format_wind_kmh, forecast.getWind().getSpeed())));

            humidityTextView
                    .setText(String.format("%s %s"
                            , context.getString(R.string.humidity_label)
                            , context.getString(R.string.format_humidity, forecast.getMain().getHumidity())));

            pressureTextView
                    .setText(String.format("%s %s"
                            , context.getString(R.string.pressure_label)
                            , context.getString(R.string.format_pressure, forecast.getMain().getPressure())));

//            lastUpdateTextView
//                    .setText(String.format("%s%s"
//                            , context.getString(R.string.last_update_label)
//                            , forecast.getLastUpdate()));
        }
    }

    class ForecastViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.weather_icon)
        ImageView weatherIconImageView;
        @BindView(R.id.date)
        TextView dateTextView;
        @BindView(R.id.weather_description)
        TextView weatherDescriptionTextView;
        @BindView(R.id.low_temperature)
        TextView lowTemperatureTextView;
        @BindView(R.id.high_temperature)
        TextView highTemperatureTextView;

        private Context context;

        ForecastViewHolder(View itemView) {
            super(itemView);

            context = itemView.getContext();
            ButterKnife.bind(this, itemView);

        }

        void bindData(Forecast forecast) {

            Weather weather = forecast.getWeather().get(0);
            String iconUrl = Utils.getWeatherIconUrl(weather.getIcon());

            Picasso.with(context).load(iconUrl).into(weatherIconImageView);
            dateTextView.setText(forecast.getDate());
            weatherDescriptionTextView.setText(weather.getDescription());

            highTemperatureTextView
                    .setText(context.getString(R.string.format_temperature, forecast.getMain().getTemp_max()));

            lowTemperatureTextView
                    .setText(context.getString(R.string.format_temperature, forecast.getMain().getTemp_min()));

        }
    }
}
