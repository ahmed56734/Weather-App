
package com.example.ahmed.weatherapp_index.data.source.remote.api.response;

import com.example.ahmed.weatherapp_index.data.models.City;
import com.example.ahmed.weatherapp_index.data.models.Forecast;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ForecastResponse {

    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private double message;
    @SerializedName("cnt")
    @Expose
    private int cnt;
    @SerializedName("list")
    @Expose
    private java.util.List<Forecast> forecast = null;
    @SerializedName("city")
    @Expose
    private City city;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ForecastResponse() {
    }

    /**
     * 
     * @param message
     * @param cnt
     * @param cod
     * @param forecast
     * @param city
     */
    public ForecastResponse(String cod, double message, int cnt, java.util.List<Forecast> forecast, City city) {
        super();
        this.cod = cod;
        this.message = message;
        this.cnt = cnt;
        this.forecast = forecast;
        this.city = city;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public java.util.List<Forecast> getForecast() {
        return forecast;
    }

    public void setForecast(java.util.List<Forecast> forecast) {
        this.forecast = forecast;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("cod", cod).append("message", message).append("cnt", cnt).append("list", forecast).append("city", city).toString();
    }

}
