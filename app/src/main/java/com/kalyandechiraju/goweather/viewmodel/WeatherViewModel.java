package com.kalyandechiraju.goweather.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ListView;

import com.kalyandechiraju.goweather.Constants;
import com.kalyandechiraju.goweather.GoWeather;
import com.kalyandechiraju.goweather.R;
import com.kalyandechiraju.goweather.adapter.ForecastAdapter;
import com.kalyandechiraju.goweather.model.Forecastday;
import com.kalyandechiraju.goweather.model.Weather;
import com.kalyandechiraju.goweather.service.WeatherAPI;

import java.util.List;

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
    private ObservableField<Boolean> didErrorOccur = new ObservableField<>(false);

    public WeatherViewModel(Context context) {
        ((GoWeather) context).getNetworkComponent().inject(this);
    }

    public void downloadWeatherData() {
        weatherAPI.getWeatherForecast(Constants.CITY).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(@NonNull Call<Weather> call, @NonNull Response<Weather> response) {
                if (response.isSuccessful()) {
                    weatherData.set(response.body());
                } else {
                    didErrorOccur.set(true);
                    Log.e(TAG, "Error");
                }
                isDataLoaded.set(true);
            }

            @Override
            public void onFailure(@NonNull Call<Weather> call, @NonNull Throwable t) {
                isDataLoaded.set(true);
                didErrorOccur.set(true);
                Log.e(TAG, "Error", t);
            }
        });
    }

    public void retryDownload() {
        didErrorOccur.set(false);
        isDataLoaded.set(false);
        downloadWeatherData();
    }

    public ObservableField<Weather> getWeatherData() {
        return weatherData;
    }

    public ObservableField<Boolean> getIsDataLoaded() {
        return isDataLoaded;
    }

    public ObservableField<Boolean> getDidErrorOccur() {
        return didErrorOccur;
    }

    public void setWeatherData(ObservableField<Weather> weatherData) {
        this.weatherData = weatherData;
    }

    @BindingAdapter({"bing:items"})
    public static void bindListItems(ListView listView, List<Forecastday> forecast) {
        if (forecast != null) {
            // Remove current day's forecast
            forecast.remove(0);
        }
        ForecastAdapter adapter = new ForecastAdapter(forecast);
        listView.setAdapter(adapter);
    }

    @BindingAdapter({"bind:visible"})
    public static void setVisible(View view, boolean visible) {
        if (!visible) {
            view.clearAnimation();
        }
        if (view.getId() == R.id.loading_image && visible) {
            view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.rotate));
        }
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
}
