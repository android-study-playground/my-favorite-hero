package com.br.myfavoritehero.features.listCharacter

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import br.com.concrete.howdoyoufeel.extensions.childAtPosition
import br.com.concrete.howdoyoufeel.extensions.waitUntil
import com.br.myfavoritehero.R
import com.br.myfavoritehero.base.BaseInstrumentedTest
import org.junit.Rule
import org.junit.Test

class ListHeroesActivityTest: BaseInstrumentedTest() {

    @get:Rule
    val activityRule = ActivityTestRule(ListHeroesActivity::class.java)

    @Test
    fun checkLoadListSuccess(){
        mockResponse200("mock/list_comics/return_success.json")

        onView(withId(R.id.listHeroes))
                .perform(isDisplayed().waitUntil())
                .check(matches(isDisplayed()))
    }

    @Test
    fun checkLoadFirstItemSuccess(){
        mockResponse200("mock/list_comics/return_success.json")

        onView(withId(R.id.listHeroes)
                .childAtPosition(0)
                .childAtPosition(0)
                .childAtPosition(1))
                .perform(isDisplayed().waitUntil())
                .check(matches(withText("3-D Man")))
    }

    @Test
    fun checkLoadErrorScreenWhenRequestFail(){
        mockResponseError401()

        onView(withId(R.id.error_screen))
                .perform(isDisplayed().waitUntil())
                .check(matches(isDisplayed()))

        onView(withId(R.id.error_image))
                .check(matches(isDisplayed()))

        onView(withId(R.id.error_text))
                .check(matches(withText("Sorry, estamos passando por dificuldades! :(")))

    }
}