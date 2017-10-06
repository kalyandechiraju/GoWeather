package com.kalyandechiraju.goweather.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kalyandechiraju.goweather.GoWeather;
import com.kalyandechiraju.goweather.R;
import com.kalyandechiraju.goweather.viewmodel.WeatherViewModel;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    WeatherViewModel weatherViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((GoWeather) getApplication()).getAppComponent().inject(this);

        weatherViewModel.downloadWeatherData();
    }
}
