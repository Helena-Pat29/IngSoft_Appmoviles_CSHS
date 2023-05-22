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
class CrearAlbumTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun createAlbumAndVerifyData() {
        // Click on the "Fetch Albums" button
        onView(ViewMatchers.withId(R.id.create_album_button))
            .perform(ViewActions.click())

        // e.g., IdlingResource or Thread.sleep() if appropriate
        Thread.sleep(5000)

        // Add checks for specific album items here
        onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.name_field),
                ViewMatchers.hasDescendant(ViewMatchers.withText("Name"))
            )
        )
        onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.cover_field),
                ViewMatchers.hasDescendant(ViewMatchers.withText("Cover"))
            )
        )
        onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.release_date_field),
                ViewMatchers.hasDescendant(ViewMatchers.withText("Release Date"))
            )
        )
        onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.description_field),
                ViewMatchers.hasDescendant(ViewMatchers.withText("Description"))
            )
        )
        onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.auto_complete_genre),
                ViewMatchers.hasDescendant(ViewMatchers.withText("Genre"))
            )
        )
        onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.auto_complete_record),
                ViewMatchers.hasDescendant(ViewMatchers.withText("Record Label"))
            )
        )
        onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.submit_button),
                ViewMatchers.hasDescendant(ViewMatchers.withText("Crear Album"))
            )
        )
    }
}