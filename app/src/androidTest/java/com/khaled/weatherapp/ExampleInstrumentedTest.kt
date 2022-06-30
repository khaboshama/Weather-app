package com.khaled.weatherapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.khaled.weatherapp", appContext.packageName)
    }

    @get:Rule
    var mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun test_navigate_to_article_details() {
//        viewModel.navigateToArticleScreenLiveData.postValue(articleItem)
//        Espresso.onView(withId(R.id.title_text_view)).check(ViewAssertions.matches(ViewMatchers.withText(title)));
//        Espresso.onView(withId(R.id.date_text_view)).check(ViewAssertions.matches(ViewMatchers.withText(publishedDate)));
//        Espresso.onView(withId(R.id.author_text_view)).check(ViewAssertions.matches(ViewMatchers.withText(author)));
//        Espresso.onView(withId(R.id.description_text_view)).check(ViewAssertions.matches(ViewMatchers.withText(description)));
//        Thread.sleep(5000)
    }

}