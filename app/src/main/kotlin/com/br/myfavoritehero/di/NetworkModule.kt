package com.br.myfavoritehero.di

import com.br.myfavoritehero.BuildConfig
import com.br.myfavoritehero.data.network.ApiService
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECTION_TIMEOUT = 15L
private const val READ_TIMEOUT = 30L

const val PROPERTY_BASE_URL = "PROPERTY_BASE_URL"

val networkModule = module {

    single {

        val httpLogInterceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) {
            httpLogInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLogInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        OkHttpClient.Builder()
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(httpLogInterceptor)
                .build()
    }

    single {
        Retrofit
                .Builder()
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(getProperty<String>(PROPERTY_BASE_URL))
                .client(get())
                .build()
    }

    single {
        val retrofit: Retrofit = get()
        retrofit.create<ApiService>(ApiService::class.java)
    }
}