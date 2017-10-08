package com.kalyandechiraju.goweather.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

/**
 * Created by kalyandechiraju on 09/10/17.
 */

@Module
public class TestAppModule {
    @Provides
    @Singleton
    Context provideAppContext() {
        return mock(Context.class);
    }
}
