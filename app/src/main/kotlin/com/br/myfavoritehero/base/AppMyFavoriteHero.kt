package com.br.myfavoritehero.base

import android.app.Application
import android.util.Log
import com.br.myfavoritehero.BuildConfig
import com.br.myfavoritehero.data.models.Hero
import com.br.myfavoritehero.util.Constants.heroes
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import timber.log.Timber
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception


class AppMyFavoriteHero: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        val collectionType = object : TypeToken<BaseResponse<Hero>>() {}.type
        val responseObject: BaseResponse<Hero> = GsonBuilder()
            .create()
            .fromJson(readJson("data/response_heroes.json"), collectionType)

        heroes = responseObject.data.results
    }

    private fun readJson(path: String): String? {
        try {
            val file = assets.open(path)
            val reader = BufferedReader(InputStreamReader(file))
            var mLines = StringBuilder()
            var test = true
            while (test){
                var line = reader.readLine()
                line?.let {
                    mLines.append(it)
                }?: run{
                    test = false
                }
            }

            return mLines.toString()
        }catch (e: Exception){
            throw e
        }
    }
}