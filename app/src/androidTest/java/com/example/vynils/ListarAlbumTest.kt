package com.example.vynils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

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
            .check(matches(withItemCount()))

        onView(allOf(withId(R.id.album_recycler_view), hasDescendant(withText("Buscando América"))))

        onView(allOf(withId(R.id.album_recycler_view), hasDescendant(withText("Poeta del pueblo"))))
    }

    @Test
    fun albumsDetailsAndVerifyData() {
        // Click on the "Listar Albumes" button
        onView(withId(R.id.fetch_albums_button))
            .perform(click())

        // e.g., IdlingResource or Thread.sleep() if appropriate
        Thread.sleep(5000)

        onView(allOf(withId(R.id.album_recycler_view), hasDescendant(withText("Queen"))))
            .perform(click())

        onView(allOf(withId(R.id.album_details), hasDescendant(withText("Queen"))))
        onView(allOf(withId(R.id.album_details), hasDescendant(withText("Queen es una banda británica de rock formada en 1970 en Londres por el cantante Freddie Mercury, el guitarrista Brian May, el baterista Roger Taylor y el bajista John Deacon. Si bien el grupo ha presentado bajas de dos de sus miembros (Mercury, fallecido en 1991, y Deacon, retirado en 1997), los integrantes restantes, May y Taylor, continúan trabajando bajo el nombre Queen, por lo que la banda aún se considera activa."))))
    }

    // Custom matcher to check the number of items in a RecyclerView
    private fun withItemCount(): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("RecyclerView with item count: 4")
            }

            override fun matchesSafely(recyclerView: RecyclerView?): Boolean {
                return recyclerView?.adapter?.itemCount == 4
            }
        }
    }

}