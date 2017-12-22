package com.mohsenmb.tvmazesampleproject.activity;

import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mohsenmb.apimodule.service.model.Show;
import com.mohsenmb.tvmazesampleproject.MainActivity;
import com.mohsenmb.tvmazesampleproject.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivity = new ActivityTestRule<>(
            MainActivity.class,
            true,
            // false: do not launch the activity immediately
            false);

    /*@Inject
    TvMazeApiService apiService;

    @Inject
    Gson gson;*/

    List<Show> expectedResponse;

    @Before
    public void setUp() {

    }

    @Test
    public void testIt() {

        mainActivity.launchActivity(new Intent());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        onView(withId(R.id.rvShows))
                .perform(RecyclerViewActions.scrollToPosition(50));

        onView(withId(R.id.rvShows))
                .perform(RecyclerViewActions.actionOnItemAtPosition(50, click()));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pressBack();

        onView(withId(R.id.rvShows))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
