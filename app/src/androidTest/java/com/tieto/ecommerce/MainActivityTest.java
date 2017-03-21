package com.tieto.ecommerce;

import android.support.test.espresso.DataInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static org.hamcrest.CoreMatchers.anything;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void productsAreVisible() throws InterruptedException {
        onView(withId(R.id.products_list))
                .check(matches(isDisplayed()));
        DataInteraction productsList = onData(anything())
                .inAdapterView(withId(R.id.products_list));

        List<String> titles = Arrays.asList("iPod", "iPod touch", "iPod nano", "Apple TV");
        int i = 0;
        for(String expectedTitle: titles) {
            productsList
                    .atPosition(i)
                    .onChildView(withId(R.id.product_list_title))
                    .check(matches(withText(expectedTitle)));
            i++;
        }
    }

    @Test
    public void selectCustomersTab() {
        onView(withText("customers")).perform(click());
        onView(withId(R.id.customers_list))
                .check(matches(isDisplayed()));
        onData(anything())
                .inAdapterView(withId(R.id.customers_list))
                .atPosition(0)
                .onChildView(withId(R.id.customer_list_last_name))
                .check(matches(withText("Wolff")));
    }

}
