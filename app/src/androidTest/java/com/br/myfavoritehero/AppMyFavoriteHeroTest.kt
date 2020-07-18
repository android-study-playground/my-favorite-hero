package com.br.myfavoritehero

import android.app.Application
import android.os.AsyncTask
import com.br.myfavoritehero.di.*
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class AppMyFavoriteHeroTest : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        RxJavaPlugins.setInitComputationSchedulerHandler {
            Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR)
        }
        RxJavaPlugins.setInitIoSchedulerHandler {
            Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        startKoin {

            androidContext(this@AppMyFavoriteHeroTest)

            modules(listOf(
                databaseModule,
                networkModule,
                repositoryModule,
                viewModelModule
            ))

            properties(mapOf(PROPERTY_BASE_URL to "http://localhost:8080/"))
        }
    }
}