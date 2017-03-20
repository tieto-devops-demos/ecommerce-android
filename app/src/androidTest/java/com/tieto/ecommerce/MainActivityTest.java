package com.tieto.ecommerce;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.endsWith;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    public static Matcher<View> childOf(final Matcher<View> parentMatcher, final int childPosition) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("position " + childPosition + " of parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                if (!(view.getParent() instanceof ViewGroup)) return false;
                ViewGroup parent = (ViewGroup) view.getParent();

                return parentMatcher.matches(parent)
                        && parent.getChildCount() > childPosition
                        && parent.getChildAt(childPosition).equals(view);
            }
        };
    }


    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void firstProductIsVisible() throws InterruptedException {

        Matcher<View> firstRow = allOf(childOf(withId(R.id.products_list), 0),
                withClassName(endsWith("RelativeLayout")));

        onView(firstRow).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.product_list_title),
                isDescendantOfA(firstRow)))
                .check(matches(withText("iPod")));
    }

    @Test
    public void firstProductIsVisible_2() throws InterruptedException {
        onView(withId(R.id.products_list))
                .check(matches(isDisplayed()));
        onData(is(instanceOf(Product.class)))
                .atPosition(0)
                .onChildView(withId(R.id.product_list_title))
                .check(matches(withText("iPod")));
        onData(is(instanceOf(Product.class)))
                .atPosition(2)
                .onChildView(withId(R.id.product_list_title))
                .check(matches(withText("iPod nano")));
    }

    @Test
    public void selectCustomersTab() {
        onView(withText("customers")).perform(click());

        onView(allOf(isDescendantOfA(withId(R.id.viewpager)),
                withClassName(endsWith("TextView")),
                isDisplayed()))
                .check(matches(withText("Customers view")));
    }
}
