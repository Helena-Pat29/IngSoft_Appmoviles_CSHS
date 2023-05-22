package com.example.vynils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetalleCollectorsTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun collectorsDetailsAndVerifyData() {
        // Click on the "Collectors" button
        Espresso.onView(ViewMatchers.withId(R.id.fetch_collectors_button))
            .perform(ViewActions.click())

        // e.g., IdlingResource or Thread.sleep() if appropriate
        Thread.sleep(5000)

        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.collector_recycler_view),
                ViewMatchers.hasDescendant(ViewMatchers.withText("Manolo Bellon"))
            )
        )
            .perform(ViewActions.click())

        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.collector_details),
                ViewMatchers.hasDescendant(ViewMatchers.withText("The most relevant album of Ruben Blades"))
            )
        )
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.collector_details),
                ViewMatchers.hasDescendant(ViewMatchers.withText("Rubén Blades Bellido de Luna"))
            )
        )
    }
}