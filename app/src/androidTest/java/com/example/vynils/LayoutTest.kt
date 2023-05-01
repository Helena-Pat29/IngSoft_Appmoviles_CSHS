package com.example.vynils

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LayoutTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(TestActivity::class.java)

    @Test
    fun checkAlbumNameDisplayed() {
        launchTestActivityWithLayout(R.layout.album_item)
        onView(withId(R.id.album_name))
            .check(matches(withText("Album Name")))
    }

    @Test
    fun checkArtistNameDisplayed() {
        launchTestActivityWithLayout(R.layout.artist_description_recycler)
        onView(withId(R.id.artist_name))
            .check(matches(withText("Artist Name")))
    }

    @Test
    fun checkAlbumDescriptionDisplayed() {
        launchTestActivityWithLayout(R.layout.album_item)
        onView(withId(R.id.album_description))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkArtistDescriptionDisplayed() {
        launchTestActivityWithLayout(R.layout.artist_description_recycler)
        onView(withId(R.id.artist_description))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkAlbumCoverIsDisplayed() {
        launchTestActivityWithLayout(R.layout.album_item)
        onView(withId(R.id.album_cover))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkArtistCoverIsDisplayed() {
        launchTestActivityWithLayout(R.layout.artist_description_recycler)
        onView(withId(R.id.artist_image))
            .check(matches(isDisplayed()))
    }

    private fun launchTestActivityWithLayout(layoutResId: Int) {
        val intent = Intent(ApplicationProvider.getApplicationContext(), TestActivity::class.java)
        intent.putExtra("layoutResId", layoutResId)
        ActivityScenario.launch<TestActivity>(intent)
    }

}
