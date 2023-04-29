package com.example.vynils

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.assertion.ViewAssertions.matches
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
        onView(withId(R.id.album_name))
            .check(matches(withText("Album Name")))
    }

    @Test
    fun checkAlbumDescriptionDisplayed() {
        onView(withId(R.id.album_description))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkAlbumCoverIsDisplayed() {
        onView(withId(R.id.album_cover))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkPerformerNameDisplayed() {
        onView(withId(R.id.performer_name))
            .check(matches(withText("Performer Name")))
    }
}
