package com.kalyandechiraju.goweather.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import com.kalyandechiraju.goweather.Constants;
import com.kalyandechiraju.goweather.GoWeather;
import com.kalyandechiraju.goweather.model.Weather;
import com.kalyandechiraju.goweather.service.WeatherAPI;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kalyandechiraju on 06/10/17.
 */

public class WeatherViewModel extends BaseViewModel {
    private static final String TAG = WeatherViewModel.class.getName();
    @Inject
    WeatherAPI weatherAPI;

    private ObservableField<Weather> weatherData = new ObservableField<>();
    private ObservableField<Boolean> isDataLoaded = new ObservableField<>(false);

    public WeatherViewModel(Context context) {
        ((GoWeather) context).getNetworkComponent().inject(this);
    }

    public void downloadWeatherData() {
        weatherAPI.getWeatherForecast(Constants.CITY).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if (response.isSuccessful()) {
                    weatherData.set(response.body());
                    Log.d(TAG, response.body().getCurrent().getTempC().toString());
                } else {
                    Log.e(TAG, "Error");
                }
                isDataLoaded.set(true);
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                isDataLoaded.set(true);
                Log.e(TAG, "Error", t);
            }
        });
    }

    public ObservableField<Weather> getWeatherData() {
        return weatherData;
    }

    public ObservableField<Boolean> getIsDataLoaded() {
        return isDataLoaded;
    }

    @BindingAdapter({"bind:visible"})
    public static void setVisible(View view, boolean visible) {
        if (!visible) {
            view.clearAnimation();
        }
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
}
