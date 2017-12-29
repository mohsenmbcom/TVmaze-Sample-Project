package com.mohsenmb.tvmazesampleproject.activity;

import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mohsenmb.tvmazesampleproject.MainActivity;
import com.mohsenmb.tvmazesampleproject.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivity = new ActivityTestRule<>(
            MainActivity.class,
            true,
            // false: do not launch the activity immediately
            false);

    @Before
    public void setUp() {

    }

    @Test
    public void testIt() {

        mainActivity.launchActivity(new Intent());

        // wait for loading the grid
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // scroll to the end of the grid
        onView(withId(R.id.rvShows))
                .perform(RecyclerViewActions.scrollToPosition(49));

        // wait for the next page loading
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // scroll to the end of the grid again
        onView(withId(R.id.rvShows))
                .perform(RecyclerViewActions.scrollToPosition(99));

        // wait for the third page loading
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // click on the 122nd item (an item of the third page) to show the details fragment
        onView(withId(R.id.rvShows))
                .perform(RecyclerViewActions.actionOnItemAtPosition(122, click()));

        // wait a half of a second :)
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // back to the grid
        pressBack();

        // wait a sec
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // end of story ;)
    }
}
