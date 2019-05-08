package com.br.myfavoritehero

import android.app.Application
import android.os.AsyncTask
import br.com.concrete.howdoyoufeel.util.BASE_URL
import com.br.myfavoritehero.di.PROPERTY_BASE_URL
import com.br.myfavoritehero.di.networkModule
import com.br.myfavoritehero.di.repositoryModule
import com.br.myfavoritehero.di.viewModelModule
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
                    viewModelModule,
                    repositoryModule,
                    networkModule
            ))

            properties(mapOf(PROPERTY_BASE_URL to BASE_URL))
        }
    }

}