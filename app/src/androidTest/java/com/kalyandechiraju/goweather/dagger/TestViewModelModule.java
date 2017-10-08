package com.kalyandechiraju.goweather.dagger;

import android.content.Context;

import com.kalyandechiraju.goweather.viewmodel.WeatherViewModel;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kalyandechiraju on 09/10/17.
 */

@Module
public class TestViewModelModule {
    @Provides
    @Singleton
    WeatherViewModel provideWeatherViewModel(Context context) {
        return Mockito.mock(WeatherViewModel.class);
    }
}
