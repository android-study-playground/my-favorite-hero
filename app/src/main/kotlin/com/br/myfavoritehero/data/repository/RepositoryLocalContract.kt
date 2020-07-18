package com.br.myfavoritehero.data.repository

import androidx.lifecycle.LiveData
import com.br.myfavoritehero.base.BaseResponse
import com.br.myfavoritehero.data.models.Hero
import io.reactivex.Observable

interface RepositoryLocalContract {

    fun getFavoriteHeroes():LiveData<List<Hero>>
    fun getAllHeroes(): Observable<BaseResponse<Hero>>
    fun getHeroes(offset : Int = 0): Observable<BaseResponse<Hero>>
    fun saveAllHeroes(heroes: ArrayList<Hero>)

    fun getHero(id: Int): Hero
    fun updateHero(hero: Hero)
    fun saveHero(hero:Hero)
}