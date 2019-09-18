package com.br.myfavoritehero.data.request

import com.br.myfavoritehero.BuildConfig
import com.br.myfavoritehero.base.BaseResponse
import com.br.myfavoritehero.data.models.Comic
import com.br.myfavoritehero.data.models.Hero
import com.br.myfavoritehero.data.network.ApiService
import com.br.myfavoritehero.util.Constants.LIMIT
import com.br.myfavoritehero.util.Constants.ONE_SECOND
import com.br.myfavoritehero.util.Util
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class Repository(private val apiService: ApiService) : RepositoryContract {

    override fun getComics(characterId: String): Observable<BaseResponse<Comic>> {
        val ts = (System.currentTimeMillis() / ONE_SECOND)
        val hash = Util.md5(ts.toString() + BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY)

        return apiService.getComics(
                ts,
                BuildConfig.PUBLIC_KEY,
                hash,
                characters = characterId)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
    }

    override fun getHeroes(): Observable<BaseResponse<Hero>> {
        val ts = (System.currentTimeMillis() / ONE_SECOND)
        val hash = Util.md5(ts.toString() + BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY)

        return apiService.getHeroes(
                ts,
                BuildConfig.PUBLIC_KEY,
                hash)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
    }

    override fun loadMore(offset: Int): Observable<BaseResponse<Hero>> {
        val ts = (System.currentTimeMillis() / ONE_SECOND)
        val hash = Util.md5(ts.toString() + BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY)

        return apiService.getHeroes(
                ts,
                BuildConfig.PUBLIC_KEY,
                hash,
                LIMIT,
                offset)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
    }
}
