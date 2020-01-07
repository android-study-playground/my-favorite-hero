package com.br.myfavoritehero.features.home

import androidx.test.rule.ActivityTestRule
import com.br.myfavoritehero.base.BaseInstrumentedTest
import org.junit.Rule
import org.junit.Test

class HomeActivityTest : BaseInstrumentedTest() {

    @get:Rule
    val activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun whenStartActivity_shouldLoadListHeroes() {
        mockResponse200("")
        //TODO IMPLEMENTAR
    }

    @Test
    fun whenClickFavorites_shouldShowFavoritesFragment() {
        mockResponse200("")
        //TODO IMPLEMENTAR
    }

}