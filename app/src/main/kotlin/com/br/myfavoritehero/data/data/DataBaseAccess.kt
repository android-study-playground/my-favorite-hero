package com.br.myfavoritehero.data.data

import com.br.myfavoritehero.data.models.Hero
import com.br.myfavoritehero.util.Constants

class DataBaseAccess{

    fun getHeroes(
        success: (lista: ArrayList<Hero>) -> Unit,
        error: (t: Throwable) -> Unit
    ){
        try {
            val listHeroes: ArrayList<Hero> = ArrayList()
            listHeroes.addAll(Constants.heroes.subList(DoAsync.offset, DoAsync.limit + DoAsync.offset))
            DoAsync.offset += DoAsync.limit
            success(listHeroes)
        }catch (e: Exception){
            error(e)
        }
    }

    fun loadMore(
        success: (lista: ArrayList<Hero>) -> Unit,
        error: (t: Throwable) -> Unit
    ){
        try {
            val listHeroes: ArrayList<Hero> = ArrayList()
            listHeroes.addAll(Constants.heroes.subList(DoAsync.offset, DoAsync.limit + DoAsync.offset))
            DoAsync.offset += DoAsync.limit
            success(listHeroes)
        }catch (e: Exception){
            error(e)
        }
    }
}
