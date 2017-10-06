package com.kalyandechiraju.goweather.module;

import android.content.Context;

import com.kalyandechiraju.goweather.viewmodel.WeatherViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kalyandechiraju on 06/10/17.
 */

@Module
public class ViewModelModule {
    @Provides
    @Singleton
    WeatherViewModel provideWeatherViewModel(Context context) {
        return new WeatherViewModel(context);
    }
}
