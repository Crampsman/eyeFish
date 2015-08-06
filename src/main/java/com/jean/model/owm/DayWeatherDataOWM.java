package com.jean.model.owm;

/**
 * Created by Alex on 06.08.15.
 */
public class DayWeatherDataOWM {

    private long dt;

    private float pressure;

    private int humidity;

    private float speed;

    private int deg;

    private int clouds;

    private AverageWeatherParamsOWM weather;

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public AverageWeatherParamsOWM getWeather() {
        return weather;
    }

    public void setWeather(AverageWeatherParamsOWM weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "DayWeatherDataOWM [dt=" + dt + ", pressure=" + pressure + ", humidity=" + humidity + ", speed=" + speed + ", deg=" + deg
                + ", clouds=" + clouds + ", weather=" + weather + "]";
    }
}
