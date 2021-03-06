package com.kalyandechiraju.goweather.module;

import com.kalyandechiraju.goweather.Constants;
import com.kalyandechiraju.goweather.service.WeatherAPI;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kalyandechiraju on 06/10/17.
 */

@Module
public class NetworkModule {
    private static final String NAMED_BASE_URL = "NAMED_BASE_URL";

    @Provides
    @Named(NAMED_BASE_URL)
    String provideBaseUrlString() {
        return Constants.BASE_URL;
    }

    @Provides
    @Singleton
    Converter.Factory provideGsonConverter() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Converter.Factory converter, @Named(NAMED_BASE_URL) String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converter)
                .build();
    }

    @Provides
    @Singleton
    WeatherAPI provideWeatherAPI(Retrofit retrofit) {
        return retrofit.create(WeatherAPI.class);
    }
}
