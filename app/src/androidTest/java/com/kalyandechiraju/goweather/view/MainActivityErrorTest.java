package com.kalyandechiraju.goweather.view;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import com.kalyandechiraju.goweather.Constants;
import com.kalyandechiraju.goweather.R;
import com.kalyandechiraju.goweather.testUtil.AssetFileReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by kalyandechiraju on 09/10/17.
 */

public class MainActivityErrorTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class, true, false);
    private MockWebServer server;

    @Before
    public void setup() throws IOException {
        server = new MockWebServer();
        server.start();
        Constants.BASE_URL = server.url("/").toString();
    }

    @Test
    public void errorPageTest() {

        server.enqueue(new MockResponse().setResponseCode(500));
        Intent intent = new Intent();
        mActivityTestRule.launchActivity(intent);

        onView(withText(R.string.error_message)).check(matches(isDisplayed()));
    }

    @Test
    public void retryButtonTest() throws IOException {

        server.enqueue(new MockResponse().setResponseCode(500));
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(AssetFileReader.readDummyResponse("dummy_success_response.json")));

        Intent intent = new Intent();
        mActivityTestRule.launchActivity(intent);

        onView(withText(R.string.error_message)).check(matches(isDisplayed()));
        onView(withText(R.string.retry_button_text)).perform(click());
        onView(withText("10" + ((char) 0x00B0))).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws IOException {
        server.shutdown();
    }


}
