package com.kalyandechiraju.goweather;

import android.app.Application;

import com.kalyandechiraju.goweather.component.AppComponent;
import com.kalyandechiraju.goweather.component.DaggerAppComponent;
import com.kalyandechiraju.goweather.component.DaggerNetworkComponent;
import com.kalyandechiraju.goweather.component.NetworkComponent;
import com.kalyandechiraju.goweather.module.AppModule;
import com.kalyandechiraju.goweather.module.NetworkModule;
import com.kalyandechiraju.goweather.module.ViewModelModule;

/**
 * Created by kalyandechiraju on 06/10/17.
 */

public class GoWeather extends Application {
    private NetworkComponent networkComponent;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (networkComponent == null) {
            networkComponent = DaggerNetworkComponent.builder()
                    .networkModule(new NetworkModule())
                    .build();
        }

        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .viewModelModule(new ViewModelModule())
                    .build();
        }
    }

    public NetworkComponent getNetworkComponent() {
        return networkComponent;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }


    // For Injecting Test Components
    public void setNetworkComponent(NetworkComponent networkComponent) {
        this.networkComponent = networkComponent;
    }

    // For Injecting Test Components
    public void setAppComponent(AppComponent appComponent) {
        this.appComponent = appComponent;
    }
}
