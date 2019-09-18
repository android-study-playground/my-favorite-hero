package com.br.myfavoritehero.util

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.br.myfavoritehero.AppMyFavoriteHeroTest

class MyFavoriteHeroTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(
                cl,
                AppMyFavoriteHeroTest::class.java.name,
                context)
    }
}