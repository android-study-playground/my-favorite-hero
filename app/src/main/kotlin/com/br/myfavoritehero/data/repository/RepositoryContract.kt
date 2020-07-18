package com.br.myfavoritehero.data.repository

import androidx.lifecycle.LiveData
import com.br.myfavoritehero.base.BaseResponse
import com.br.myfavoritehero.data.models.Comic
import com.br.myfavoritehero.data.models.Hero
import io.reactivex.Observable

interface RepositoryContract {
    fun getComics(characterId: String): Observable<BaseResponse<Comic>>
    fun getHeroes(): Observable<BaseResponse<Hero>>
    fun loadMore(offset: Int): Observable<BaseResponse<Hero>>
    fun getHero(id:Int): Hero
    fun updateHero(hero:Hero)
    fun getFavoriteHeroes(): LiveData<List<Hero>>
}