package com.br.myfavoritehero.features.listCharacter.viewModel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.br.myfavoritehero.base.BaseViewModel
import com.br.myfavoritehero.data.dao.HeroDao
import com.br.myfavoritehero.data.models.Hero
import com.br.myfavoritehero.data.models.ViewStateModel
import com.br.myfavoritehero.data.request.Repository

class ListHeroesViewModel(private val repository: Repository, private val heroDao: HeroDao): BaseViewModel() {

    private val viewStateResponse: MutableLiveData<ViewStateModel<ArrayList<Hero>>> = MutableLiveData()
    private val viewStateResponseLoadMore: MutableLiveData<ViewStateModel<ArrayList<Hero>>> = MutableLiveData()

    fun getHeroes() = viewStateResponse
    fun getMore() = viewStateResponseLoadMore

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun loadHeroes(){
        viewStateResponse.postValue(ViewStateModel(ViewStateModel.Status.LOADING))
        repository.getHeroes(
            { base ->
                heroDao.saveAll(base.data.results)
                viewStateResponse.postValue(ViewStateModel(status = ViewStateModel.Status.SUCCESS, model = base.data.results))
            }, {error ->
                viewStateResponse.postValue(ViewStateModel(status = ViewStateModel.Status.ERROR, errors = notKnownError(error)))
            }
        )
    }

    fun loadMore(offset: Int){
        viewStateResponseLoadMore.postValue(ViewStateModel(ViewStateModel.Status.LOADING))
        repository.loadMore(
            { base ->
                heroDao.saveAll(base.data.results)
                viewStateResponseLoadMore.postValue(ViewStateModel(status = ViewStateModel.Status.SUCCESS, model = base.data.results))
            }, {error ->
                viewStateResponseLoadMore.postValue(ViewStateModel(status = ViewStateModel.Status.ERROR, errors = notKnownError(error)))
            },
            offset
        )
    }

}