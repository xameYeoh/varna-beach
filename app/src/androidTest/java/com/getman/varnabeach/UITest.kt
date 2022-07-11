package com.getman.varnabeach

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.getman.varnabeach.recycler.BeachViewHolder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@Suppress("DEPRECATION")
@RunWith(AndroidJUnit4::class)
class UITest {
    @get:Rule
    val testRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java, false, false)

    @Test
    fun whenCardClick_goesToConditionsScreen() {
        testRule.launchActivity(null)

        Espresso.onView(withId(R.id.list))
            .perform(RecyclerViewActions.actionOnItemAtPosition<BeachViewHolder>(0, click()))

        Espresso.onView(withId(R.id.beach_card))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}