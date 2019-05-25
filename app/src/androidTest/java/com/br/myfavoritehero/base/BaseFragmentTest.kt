package com.br.myfavoritehero.base

import androidx.fragment.app.Fragment
import androidx.test.rule.ActivityTestRule
import com.br.myfavoritehero.debug.BaseTestActivity
import org.junit.Before
import org.junit.Rule

abstract class BaseFragmentTest : BaseInstrumentedTest() {

    lateinit var testFragment: Fragment

    @get:Rule
    val activityRule = ActivityTestRule(BaseTestActivity::class.java)

    abstract fun setupFragment()

    @Before
    open fun before() {
        setupFragment()

        testFragment.let {
            activityRule.activity.setFragment(it)
        }
    }
}