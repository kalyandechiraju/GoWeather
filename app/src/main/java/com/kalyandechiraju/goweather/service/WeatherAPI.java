package com.kalyandechiraju.goweather.service;

import com.kalyandechiraju.goweather.Constants;
import com.kalyandechiraju.goweather.model.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kalyandechiraju on 06/10/17.
 */

public interface WeatherAPI {

    @GET("/forecast.json?key=" + Constants.API_KEY + "&days=" + Constants.FORECAST_DAYS)
    Call<Weather> getWeatherForecast(@Query("q") String city);

}
