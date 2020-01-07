package com.br.myfavoritehero.features.listCharacter

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.br.myfavoritehero.R
import com.br.myfavoritehero.base.BaseInstrumentedTest
import com.br.myfavoritehero.extensions.childAtPosition
import com.br.myfavoritehero.extensions.waitUntil
import org.junit.Test

class FavoriteHeroesFragmentTest : BaseInstrumentedTest() {

    @Test
    fun checkLoadListSuccess() {
        //TODO MOCK ROOM CALL

        launchFragmentInContainer<FavoriteHeroesFragment>(themeResId = R.style.AppTheme)

        onView(withId(R.id.listHeroes))
            .perform(isDisplayed().waitUntil())
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkLoadFirstItemSuccess() {
        //TODO MOCK ROOM CALL

        launchFragmentInContainer<FavoriteHeroesFragment>(themeResId = R.style.AppTheme)

        onView(
            withId(R.id.listHeroes)
                .childAtPosition(0)
                .childAtPosition(0)
                .childAtPosition(1)
        )
            .perform(isDisplayed().waitUntil())
            .check(matches(withText("Moon Girl (Lunella Layfayette)")))
    }

    @Test
    fun checkLoadErrorScreenWhenRequestFail() {
        //TODO MOCK ROOM CALL

        launchFragmentInContainer<FavoriteHeroesFragment>(themeResId = R.style.AppTheme)

        onView(withId(R.id.error_screen))
            .perform(isDisplayed().waitUntil())
            .check(matches(isDisplayed()))

        onView(withId(R.id.error_image))
            .check(matches(isDisplayed()))

        onView(withId(R.id.error_text))
            .check(matches(withText("Sorry, estamos passando por dificuldades! :(")))
    }
}