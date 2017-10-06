package com.kalyandechiraju.goweather.component;

import com.kalyandechiraju.goweather.module.AppModule;
import com.kalyandechiraju.goweather.module.ViewModelModule;
import com.kalyandechiraju.goweather.view.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by kalyandechiraju on 06/10/17.
 */

@Singleton
@Component(modules = {ViewModelModule.class, AppModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
