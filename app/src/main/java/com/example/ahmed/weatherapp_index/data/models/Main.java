
package com.example.ahmed.weatherapp_index.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Main {

    @SerializedName("temp")
    @Expose
    private double temp;
    @SerializedName("temp_min")
    @Expose
    private double temp_min;
    @SerializedName("temp_max")
    @Expose
    private double temp_max;
    @SerializedName("pressure")
    @Expose
    private double pressure;
    @SerializedName("sea_level")
    @Expose
    private double sea_level;
    @SerializedName("grnd_level")
    @Expose
    private double grnd_level;
    @SerializedName("humidity")
    @Expose
    private int humidity;
    @SerializedName("temp_kf")
    @Expose
    private int temp_kf;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Main() {
    }

    /**
     * 
     * @param temp_kf
     * @param humidity
     * @param pressure
     * @param temp_max
     * @param sea_level
     * @param temp_min
     * @param temp
     * @param grnd_level
     */
    public Main(double temp, double temp_min, double temp_max, double pressure, double sea_level, double grnd_level, int humidity, int temp_kf) {
        super();
        this.temp = temp;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.pressure = pressure;
        this.sea_level = sea_level;
        this.grnd_level = grnd_level;
        this.humidity = humidity;
        this.temp_kf = temp_kf;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getSea_level() {
        return sea_level;
    }

    public void setSea_level(double sea_level) {
        this.sea_level = sea_level;
    }

    public double getGrnd_level() {
        return grnd_level;
    }

    public void setGrnd_level(double grnd_level) {
        this.grnd_level = grnd_level;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getTemp_kf() {
        return temp_kf;
    }

    public void setTemp_kf(int temp_kf) {
        this.temp_kf = temp_kf;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("temp", temp).append("temp_min", temp_min).append("temp_max", temp_max).append("pressure", pressure).append("sea_level", sea_level).append("grnd_level", grnd_level).append("humidity", humidity).append("temp_kf", temp_kf).toString();
    }

}
