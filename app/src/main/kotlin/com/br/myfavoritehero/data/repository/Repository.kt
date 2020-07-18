package com.br.myfavoritehero.data.repository

import androidx.lifecycle.LiveData
import com.br.myfavoritehero.base.BaseResponse
import com.br.myfavoritehero.data.models.Comic
import com.br.myfavoritehero.data.models.Hero
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


class Repository(
    private val repositoryRemote: RepositoryRemoteContract,
    private val repositoryLocal: RepositoryLocalContract
) : RepositoryContract {

    override fun getHero(id: Int): Hero {
        return repositoryLocal.getHero(id)
    }

    override fun updateHero(hero: Hero) {
        repositoryLocal.updateHero(hero)
    }

    override fun getFavoriteHeroes(): LiveData<List<Hero>> {
        return repositoryLocal.getFavoriteHeroes()
    }

    override fun getComics(characterId: String): Observable<BaseResponse<Comic>> {
        return repositoryRemote.getComics(characterId)
    }

    override fun getHeroes(): Observable<BaseResponse<Hero>> {
        return Observable.mergeDelayError(
            repositoryRemote.getHeroes().doOnNext { repositoryLocal.saveAllHeroes(it.data.results) }.subscribeOn(
                Schedulers.io()
            ),
            repositoryLocal.getHeroes(0).subscribeOn(Schedulers.io())
        )
    }

    override fun loadMore(offset: Int): Observable<BaseResponse<Hero>> {
        return Observable.mergeDelayError(
            repositoryRemote.loadMore(offset).doOnNext { repositoryLocal.saveAllHeroes(it.data.results) }.subscribeOn(
                Schedulers.io()
            ),
            repositoryLocal.getHeroes(offset).subscribeOn(Schedulers.io())
        )
    }
}
