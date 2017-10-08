package com.kalyandechiraju.goweather.dagger;

import com.kalyandechiraju.goweather.component.AppComponent;
import com.kalyandechiraju.goweather.view.MainActivityTest;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by kalyandechiraju on 09/10/17.
 */

@Singleton
@Component(modules = {TestViewModelModule.class, TestAppModule.class})
public interface TestAppComponent extends AppComponent {
    void inject(MainActivityTest mainActivityTest);
}
