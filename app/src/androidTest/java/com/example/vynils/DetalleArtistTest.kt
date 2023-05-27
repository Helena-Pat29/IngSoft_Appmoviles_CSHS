package com.example.vynils

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetalleArtistTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun artistDetailsAndVerifyData() {
        // Click on the "Listar Artist" button
        Espresso.onView(ViewMatchers.withId(R.id.fetch_artist_button))
            .perform(ViewActions.click())

        // e.g., IdlingResource or Thread.sleep() if appropriate
        Thread.sleep(5000)

        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.artist_recycler_view),
                ViewMatchers.hasDescendant(ViewMatchers.withText("Rubén Blades Bellido de Luna"))
            )
        )
            .perform(ViewActions.click())

        Thread.sleep(5000)

        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.artist_details),
                ViewMatchers.hasDescendant(ViewMatchers.withText("Rubén Blades Bellido de Luna"))
            )
        )
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.artist_details),
                ViewMatchers.hasDescendant(ViewMatchers.withText("Es un cantante, compositor, músico, actor, abogado, político y activista panameño. Ha desarrollado gran parte de su carrera artística en la ciudad de Nueva York."))
            )
        )
    }
}