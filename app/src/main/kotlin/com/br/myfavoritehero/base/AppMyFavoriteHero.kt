package com.br.myfavoritehero.base

import android.app.Application
import com.br.myfavoritehero.BuildConfig
import com.br.myfavoritehero.di.*
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class AppMyFavoriteHero: Application() {

    override fun onCreate() {
        super.onCreate()

        //Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        //Koin
        startKoin(this,
            listOf(
                networkModule,
                repositoryModule,
                viewModelModule,
                databaseModule
            ),
            extraProperties = mapOf(PROPERTY_BASE_URL to BuildConfig.BASE_URL))
    }
}