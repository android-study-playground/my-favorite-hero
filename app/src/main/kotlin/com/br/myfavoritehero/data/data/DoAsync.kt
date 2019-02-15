package com.br.myfavoritehero.data.data

import android.os.AsyncTask
import com.br.myfavoritehero.data.models.Hero
import com.br.myfavoritehero.util.Constants
import kotlin.random.Random

class DoAsync(
    val success: (lista: ArrayList<Hero>) -> Unit,
    var error: (t: Throwable) -> Unit
) : AsyncTask<Void, Void, String>() {


    companion object {
        var offset = 0
        var limit = 50

        fun load(
            success: (lista: ArrayList<Hero>) -> Unit,
            error: (t: Throwable) -> Unit
        ){
            Thread.sleep(Random.nextInt(1500, 3000).toLong())
            try {
                val listHeroes: ArrayList<Hero> = ArrayList()
                listHeroes.addAll(Constants.heroes.subList(offset, limit + offset))
                offset += limit
                success(listHeroes)
            }catch (e: Exception){
                error(e)
            }
        }
    }

    override fun doInBackground(vararg suc: Void): String? {
        load(success, error)
        return null
    }
}