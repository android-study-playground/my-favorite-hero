package com.br.myfavoritehero.base

import android.app.Application
import com.br.myfavoritehero.BuildConfig
import timber.log.Timber

class AppMyFavoriteHero: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}