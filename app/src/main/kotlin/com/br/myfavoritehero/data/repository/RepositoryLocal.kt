package com.br.myfavoritehero.data.repository

import androidx.lifecycle.LiveData
import com.br.myfavoritehero.base.BaseResponse
import com.br.myfavoritehero.data.dao.HeroDao
import com.br.myfavoritehero.data.models.Data
import com.br.myfavoritehero.data.models.Hero
import com.br.myfavoritehero.data.models.ViewStateModel
import com.br.myfavoritehero.util.Constants.LIMIT
import io.reactivex.Observable

class RepositoryLocal(
    private val heroDao: HeroDao
) : RepositoryLocalContract {

    override fun getFavoriteHeroes(): LiveData<List<Hero>> {
        return heroDao.getFavorites()
    }

    override fun getAllHeroes(): Observable<BaseResponse<Hero>>{
        val response = BaseResponse(0, ViewStateModel.Status.SUCCESS.toString(), "", "", "", Data<Hero>(), "")
        return Observable.just(response)
    }

    override fun getHeroes(offset: Int): Observable<BaseResponse<Hero>> {
        val myOffset = offset * LIMIT
        val heroes:ArrayList<Hero> = ArrayList(heroDao.getAll(myOffset))
        val result = Data(offset, LIMIT, 0, 0, heroes)
        val response = BaseResponse(0, ViewStateModel.Status.SUCCESS.toString(), "", "", "", result, "")
        return Observable.just(response)
    }

    override fun saveAllHeroes(heroes: ArrayList<Hero>) {
        return heroDao.saveAll(heroes)
    }

    override fun getHero(id: Int): Hero {
        return heroDao.get(id)
    }

    override fun updateHero(hero: Hero) {
        return heroDao.update(hero)
    }

    override fun saveHero(hero: Hero) {
        heroDao.save(hero)
    }

}
