package com.kalyandechiraju.goweather.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.kalyandechiraju.goweather.GoWeather;
import com.kalyandechiraju.goweather.R;
import com.kalyandechiraju.goweather.databinding.ActivityMainBinding;
import com.kalyandechiraju.goweather.viewmodel.WeatherViewModel;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    WeatherViewModel weatherViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ((GoWeather) getApplication()).getAppComponent().inject(this);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setWeatherViewModel(weatherViewModel);

        ImageView imageView = (ImageView) findViewById(R.id.loading_image);
        imageView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate));

        weatherViewModel.downloadWeatherData();
    }
}
