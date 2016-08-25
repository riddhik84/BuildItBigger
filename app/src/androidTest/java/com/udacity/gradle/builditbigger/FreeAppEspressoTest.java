package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by RKs on 8/21/2016.
 */

@RunWith(AndroidJUnit4.class)
public class FreeAppEspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void jokeTest() {
        try {
            //Wait till app appears properly
            Thread.sleep(5000);
            //Click the joke button
            onView(withId(R.id.joke_button)).perform(click());
            //Wait for interstitial ad
            Thread.sleep(15000);
            //Close interstitial ad
            onView(withContentDescription("Interstitial close button")).perform(click());
            //Wait till the joke loads
            Thread.sleep(10000);
            //Check if the joke appears
            onView(withId(R.id.joke_text)).check(matches(withText(containsString("Joke"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int getResourceId(String s) {
        Context targetContext = InstrumentationRegistry.getTargetContext();
        String packageName = targetContext.getPackageName();
        return targetContext.getResources().getIdentifier(s, "id", packageName);
    }
}
