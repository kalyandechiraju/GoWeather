package com.kalyandechiraju.goweather.component;

import com.kalyandechiraju.goweather.module.NetworkModule;
import com.kalyandechiraju.goweather.viewmodel.WeatherViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by kalyandechiraju on 06/10/17.
 */

@Singleton
@Component(modules = {NetworkModule.class})
public interface NetworkComponent {
    void inject(WeatherViewModel viewModel);
}
