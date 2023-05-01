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
class ListarArtistTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun listArtistsAndVerifyData() {
        // Click on the "Listar Artists" button
        Espresso.onView(ViewMatchers.withId(R.id.fetch_artist_button))
            .perform(ViewActions.click())

        // e.g., IdlingResource or Thread.sleep() if appropriate
        Thread.sleep(5000)

        Espresso.onView(ViewMatchers.withId(R.id.artist_recycler_view))
            .check(ViewAssertions.matches(withItemCount(1)))

        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.artist_recycler_view),
                ViewMatchers.hasDescendant(ViewMatchers.withText("Rubén Blades Bellido de Luna"))
            )
        )
    }

    // Custom matcher to check the number of items in a RecyclerView
    fun withItemCount(expectedCount: Int): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("RecyclerView with item count: $expectedCount")
            }

            override fun matchesSafely(recyclerView: RecyclerView?): Boolean {
                return recyclerView?.adapter?.itemCount == expectedCount
            }
        }
    }
}