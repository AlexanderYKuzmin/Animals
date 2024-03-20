package com.kuzmin.animals.ui

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kuzmin.animals.R
import com.kuzmin.animals.feature.home.ui.adapters.ParentAdapter.ParentViewHolder
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var navController: TestNavHostController
    private lateinit var scenario: ActivityScenario<MainActivity>

    private lateinit var idlingResource: CountingIdlingResource

    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        scenario.onActivity {
            navController.setGraph(R.navigation.mobile_navigation)
            Navigation.setViewNavController(it.requireViewById(R.id.nav_view), navController)

            idlingResource = CountingIdlingResource("idle resource")
        }
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun displayFavoritesWhenNavigationItemPressed() {
        onView(
            allOf(
                withId(com.kuzmin.animals.common.R.id.favorite_nav_graph),
                isDescendantOfA(withId(R.id.nav_view))
            )
        )
            .perform(click())
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun displaySettingsWhenNavigationItemPressed() {
        onView(
            allOf(
                withId(com.kuzmin.animals.common.R.id.settings_nav_graph),
                isDescendantOfA(withId(R.id.nav_view))
            )
        )
            .perform(click())
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun displayAnimalFragmentWhenAnimalChose() {
        IdlingRegistry.getInstance().register(idlingResource)

        onView(
            withId(com.kuzmin.animals.feature.home.R.id.rv_parent)
        )
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ParentViewHolder>(0, click())
            )

        IdlingRegistry.getInstance().unregister(idlingResource)
    }
}