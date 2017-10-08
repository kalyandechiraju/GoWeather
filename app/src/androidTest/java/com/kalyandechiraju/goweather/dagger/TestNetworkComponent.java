package com.kalyandechiraju.goweather.dagger;

import com.kalyandechiraju.goweather.component.NetworkComponent;
import com.kalyandechiraju.goweather.viewmodel.WeatherViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by kalyandechiraju on 09/10/17.
 */

@Singleton
@Component(modules = {TestNetworkModule.class})
public interface TestNetworkComponent extends NetworkComponent{
    void inject(WeatherViewModel viewModel);
    //void inject(MainActivityTest mainActivityTest);
}
