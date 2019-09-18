package com.br.myfavoritehero.features.listCharacter.viewModel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.br.myfavoritehero.base.BaseViewModel
import com.br.myfavoritehero.data.dao.HeroDao
import com.br.myfavoritehero.data.models.Hero
import com.br.myfavoritehero.data.models.ViewStateModel
import com.br.myfavoritehero.data.request.RepositoryContract

class ListHeroesViewModel(private val repository: RepositoryContract ,private val heroDao: HeroDao) : BaseViewModel() {

    private val viewStateResponse: MutableLiveData<ViewStateModel<ArrayList<Hero>>> = MutableLiveData()
    private val viewStateResponseLoadMore: MutableLiveData<ViewStateModel<ArrayList<Hero>>> = MutableLiveData()

    fun getHeroes() = viewStateResponse
    fun getMore() = viewStateResponseLoadMore

    fun updateHero(hero:Hero) = heroDao.update(hero)

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun loadHeroes() {
        viewStateResponse.postValue(ViewStateModel(ViewStateModel.Status.LOADING))
        disposables.add(repository.getHeroes().subscribe(
            { base ->
                heroDao.saveAll(base.data.results)
                viewStateResponse.postValue(ViewStateModel(status = ViewStateModel.Status.SUCCESS, model = base.data.results))
            },{ error ->
                viewStateResponse.postValue(ViewStateModel(status = ViewStateModel.Status.ERROR, errors = errorHandler(error)))
            }
        ))
    }

    fun loadMore(offset: Int) {
        viewStateResponseLoadMore.postValue(ViewStateModel(ViewStateModel.Status.LOADING))
        disposables.add(repository.loadMore(offset).subscribe(
            { base ->
                heroDao.saveAll(base.data.results)
                viewStateResponseLoadMore.postValue(ViewStateModel(status = ViewStateModel.Status.SUCCESS, model = base.data.results))
            },{ error ->
                viewStateResponseLoadMore.postValue(ViewStateModel(status = ViewStateModel.Status.ERROR, errors = errorHandler(error)))
            }
        ))
    }

}