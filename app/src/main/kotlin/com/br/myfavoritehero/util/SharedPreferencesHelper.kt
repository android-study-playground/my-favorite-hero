package com.br.myfavoritehero.util

import android.content.Context
import android.preference.PreferenceManager
import com.google.gson.Gson
import timber.log.Timber

object SharedPreferencesHelper {

    private const val FAVORITE_PREFS = "FAVORITE_PREFS"
    private const val HATE_PREFS = "HATE_PREFS"

    fun savePrefs(context: Context, itens : List<Int>, sharedPrefName: String){
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = prefs.edit()
        editor.putString(sharedPrefName, Gson().toJson(itens))
        editor.apply()
    }

    private fun getFromPrefs(context: Context, sharedPrefName: String): List<Int> {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        var result: List<Int> = ArrayList()
        try {
            val json = sharedPrefs.getString(sharedPrefName, "")
            val strArr = Gson().fromJson<Array<Int>>(json, Array<Int>::class.java)
            if (strArr != null) {
                result = strArr.asList()
            }
        } catch (e: Exception) {
            Timber.d("ERROR GETTING PREFERENCES")
        }
        return result
    }

    fun setFavorite(context: Context, id: Int, active: Boolean = true){
        var favorites = getFromPrefs(context, FAVORITE_PREFS)
        if (active) {
            favorites += id
        }else{
            favorites -= id
        }
        savePrefs(context, favorites, FAVORITE_PREFS)
    }

    fun setHate(context: Context, id: Int, active: Boolean = true){
        var hates = getFromPrefs(context, HATE_PREFS)
        if (active) {
            hates += id
        }else{
            hates -= id
        }
        savePrefs(context, hates, HATE_PREFS)
    }

    fun isFavorited(context: Context, id: Int): Boolean{
        val favorites = getFromPrefs(context, FAVORITE_PREFS)
        return favorites.contains(id)
    }

    fun isHated(context: Context, id: Int): Boolean{
        val hates = getFromPrefs(context, HATE_PREFS)
        return hates.contains(id)
    }
}