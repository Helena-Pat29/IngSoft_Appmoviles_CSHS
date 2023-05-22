package com.example.vynils

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CrearPremioTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun createPrizeAndVerifyData() {
        // Click on the "Fetch Collections" button
        onView(ViewMatchers.withId(R.id.post_prize_button))
            .perform(ViewActions.click())

        // e.g., IdlingResource or Thread.sleep() if appropriate
        Thread.sleep(5000)

        // Add checks for specific collection items here
        onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.post_prize_name),
                ViewMatchers.hasDescendant(ViewMatchers.withText("Nombre"))
            )
        )
        onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.post_prize_organization),
                ViewMatchers.hasDescendant(ViewMatchers.withText("Organizacion"))
            )
        )
        onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.post_prize_description),
                ViewMatchers.hasDescendant(ViewMatchers.withText("Descripcion"))
            )
        )
        onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.post_prize_button_cancel),
                ViewMatchers.hasDescendant(ViewMatchers.withText("Cancel"))
            )
        )
        onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.post_prize_button),
                ViewMatchers.hasDescendant(ViewMatchers.withText("Crear premio"))
            )
        )
    }
}