package com.kalyandechiraju.goweather.view;


import android.app.Instrumentation;
import android.databinding.ObservableField;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.kalyandechiraju.goweather.GoWeather;
import com.kalyandechiraju.goweather.R;
import com.kalyandechiraju.goweather.dagger.DaggerTestAppComponent;
import com.kalyandechiraju.goweather.dagger.DaggerTestNetworkComponent;
import com.kalyandechiraju.goweather.dagger.TestAppComponent;
import com.kalyandechiraju.goweather.dagger.TestAppModule;
import com.kalyandechiraju.goweather.dagger.TestNetworkComponent;
import com.kalyandechiraju.goweather.dagger.TestNetworkModule;
import com.kalyandechiraju.goweather.dagger.TestViewModelModule;
import com.kalyandechiraju.goweather.viewmodel.WeatherViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import javax.inject.Inject;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Inject
    WeatherViewModel mockWeatherModel;
//    @Inject
//    WeatherAPI weatherAPI;

    @Before
    public void setup() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        GoWeather app = (GoWeather) instrumentation.getTargetContext().getApplicationContext();

        TestAppComponent testAppComponent = DaggerTestAppComponent.builder()
                .testAppModule(new TestAppModule()).testViewModelModule(new TestViewModelModule()).build();

        TestNetworkComponent testNetworkComponent = DaggerTestNetworkComponent.builder().testNetworkModule(new TestNetworkModule()).build();
        app.setAppComponent(testAppComponent);
        app.setNetworkComponent(testNetworkComponent);
        testAppComponent.inject(this);
        //testNetworkComponent.inject(this);
    }

    @Test
    public void mainActivityTest() {

        //Mockito.when(weatherAPI.getWeatherForecast(Constants.CITY)).thenReturn();
        onView(withId(R.id.temperature_textview)).check(matches(isDisplayed()));

        //Mockito.when(mockWeatherModel.getWeatherData()).thenReturn()
//
//        mActivityTestRule.getActivity().imageView.clearAnimation();
//        mActivityTestRule.getActivity().imageView.setVisibility(View.GONE);
    }

    @Test
    public void errorPageTest() {
        Mockito.when(mockWeatherModel.getDidErrorOccur()).thenReturn(new ObservableField<Boolean>(true));
        onView(withText(R.string.error_message)).check(matches(isDisplayed()));
    }
}
