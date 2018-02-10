
package com.example.ahmed.weatherapp_index.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

import android.text.format.DateFormat;
import java.util.Date;

public class Forecast {

    @SerializedName("dt")
    @Expose
    private Long dt;
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("weather")
    @Expose
    private java.util.List<Weather> weather = null;
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("snow")
    @Expose
    private Snow snow;

    @SerializedName("dt_txt")
    @Expose
    private String dt_txt;



    /**
     * No args constructor for use in serialization
     * 
     */
    public Forecast() {
    }

    /**
     *  @param dt
     * @param main
     * @param weather
     * @param clouds
     * @param wind
     * @param snow
     * @param dt_txt
     */
    public Forecast(Long dt, Main main, java.util.List<Weather> weather, Clouds clouds, Wind wind, Snow snow, String dt_txt) {
        super();
        this.dt = dt;
        this.main = main;
        this.weather = weather;
        this.clouds = clouds;
        this.wind = wind;
        this.snow = snow;
        this.dt_txt = dt_txt;
    }

    public Long getDt() {
        return dt*1000 ; //return time in mills
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public java.util.List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(java.util.List<Weather> weather) {
        this.weather = weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Snow getSnow() {
        return snow;
    }

    public void setSnow(Snow snow) {
        this.snow = snow;
    }


    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("dt", dt).append("main", main).append("weather", weather).append("clouds", clouds).append("wind", wind).append("snow", snow).append("dt_txt", dt_txt).toString();
    }

    public String getDayName() {

        return (String) DateFormat.format("EEEE", new Date(getDt()));
    }

    public String getDayNum(){
        return (String) DateFormat.format("d", new Date(getDt()));
    }

    public String getDate(){
        return (String) DateFormat.format("E,MMM d", new Date(getDt()));
    }
}
