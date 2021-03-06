package com.example.byg.exam_0120.interfaces;

import com.example.byg.exam_0120.models.Weather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by byg on 2017-02-16.
 */

public interface WeatherApi {

    String BASE_URL = "https://gist.githubusercontent.com/junsuk5/6b293ac781b038366419ac6e4027abb7/raw/b30deab47a9d2fd04247d9d912df3a9a4f7be8a9/";

    @GET("weather.json")
    Call<List<Weather>> getWeatherList();

}
