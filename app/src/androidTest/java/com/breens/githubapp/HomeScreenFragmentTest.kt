package com.breens.githubapp

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.breens.githubapp.presentation.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenFragmentTest {

    @Rule
    @JvmField
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun `searchInput_is_displayed`() {
        Espresso.onView(withId(R.id.searchInputLayout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun `user_profile_is_displayed`() {
        Espresso.onView(withId(R.id.profileInfoContainer))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}