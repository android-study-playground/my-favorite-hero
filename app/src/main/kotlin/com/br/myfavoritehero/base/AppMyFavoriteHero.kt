package com.br.myfavoritehero.base

import android.app.Application
import com.br.myfavoritehero.BuildConfig
import com.br.myfavoritehero.di.PROPERTY_BASE_URL
import com.br.myfavoritehero.di.networkModule
import com.br.myfavoritehero.di.repositoryModule
import com.br.myfavoritehero.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class AppMyFavoriteHero : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {

            androidContext(this@AppMyFavoriteHero)

            modules(listOf(
                    viewModelModule,
                    repositoryModule,
                    networkModule
            ))

            properties(mapOf(PROPERTY_BASE_URL to BuildConfig.BASE_URL))
        }
    }
}