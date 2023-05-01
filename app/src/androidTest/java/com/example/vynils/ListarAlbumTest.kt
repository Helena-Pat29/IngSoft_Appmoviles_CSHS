package com.example.vynils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.vynils.ui.album.AlbumAdapter
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.StringDescription

@RunWith(AndroidJUnit4::class)
class ListarAlbumTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun listAlbumsAndVerifyData() {
        // Click on the "Listar Albumes" button
        onView(withId(R.id.fetch_albums_button))
            .perform(click())

        // e.g., IdlingResource or Thread.sleep() if appropriate
        Thread.sleep(5000)

        onView(withId(R.id.album_recycler_view))
            .check(matches(withItemCount(4)))

        onView(allOf(withId(R.id.album_recycler_view), hasDescendant(withText("Buscando América"))))

        onView(allOf(withId(R.id.album_recycler_view), hasDescendant(withText("Poeta del pueblo"))))
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