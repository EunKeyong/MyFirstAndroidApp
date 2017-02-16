package com.example.byg.exam_0120.models;

/**
 * Created by byg on 2017-02-07.
 */

public class Weather {
    private int imageRes;
    private String weather;
    private String country;
    private String temperature;

    public Weather(int imageRes, String country, String temperature) {
        this.imageRes = imageRes;
        this.country = country;
        this.temperature = temperature;
    }

    public String getWeather() {
        return weather;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Weather{");
        sb.append("imageRes=").append(imageRes);
        sb.append(", weather='").append(weather).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", temperature='").append(temperature).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
