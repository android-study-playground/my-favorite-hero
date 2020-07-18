package com.br.myfavoritehero.data.repository

import com.br.myfavoritehero.base.BaseResponse
import com.br.myfavoritehero.data.models.Comic
import com.br.myfavoritehero.data.models.Hero
import io.reactivex.Observable

interface RepositoryRemoteContract {
    fun getComics(characterId: String): Observable<BaseResponse<Comic>>
    fun getHeroes(): Observable<BaseResponse<Hero>>
    fun loadMore(offset: Int): Observable<BaseResponse<Hero>>
}