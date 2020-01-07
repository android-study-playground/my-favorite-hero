package com.br.myfavoritehero.features.listComics

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.br.myfavoritehero.R
import com.br.myfavoritehero.base.BaseInstrumentedTest
import com.br.myfavoritehero.extensions.childAtPosition
import com.br.myfavoritehero.extensions.waitUntil
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ComicsFragmentTest : BaseInstrumentedTest() {

    @Test
    fun whenListComicsLoadingFail_shouldShowEmptyScreen() {
        mockResponseError401()

        launchFragmentInContainer<ComicsFragment>(themeResId = R.style.AppTheme)

        onView(withId(R.id.comics_label)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.comics_divider)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.comics_list)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun whenDisplayListComicsSuccess_shouldShowFirstItem() {
        mockResponse200("mock/list_comics/return_success.json")

        launchFragmentInContainer<ComicsFragment>(themeResId = R.style.AppTheme)

        onView(
            withId(R.id.comics_list)
                .childAtPosition(0)
                .childAtPosition(1)
        )
            .perform(isDisplayed().waitUntil())
            .check(matches(withText("Marvel Age Spider-Man Vol. 2: Everyday Hero (Digest)")))
    }
}