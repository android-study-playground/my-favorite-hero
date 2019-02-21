package com.br.myfavoritehero.features.listCharacter

import androidx.lifecycle.MutableLiveData
import com.br.myfavoritehero.base.BaseViewModel
import com.br.myfavoritehero.data.models.Hero
import com.br.myfavoritehero.data.models.ViewStateModel
import com.br.myfavoritehero.data.request.Repository

class ListHeroesViewModel(private val repository: Repository): BaseViewModel() {

    private val viewStateResponse: MutableLiveData<ViewStateModel<ArrayList<Hero>>> = MutableLiveData()
    private val viewStateResponseLoadMore: MutableLiveData<ViewStateModel<ArrayList<Hero>>> = MutableLiveData()


    fun getHeroes() = viewStateResponse
    fun getMore() = viewStateResponseLoadMore

    fun loadHeroes(){
        viewStateResponse.postValue(ViewStateModel(ViewStateModel.Status.LOADING))
        repository.getHeroes(
            { base ->
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
                viewStateResponseLoadMore.postValue(ViewStateModel(status = ViewStateModel.Status.SUCCESS, model = base.data.results))
            }, {error ->
                viewStateResponseLoadMore.postValue(ViewStateModel(status = ViewStateModel.Status.ERROR, errors = notKnownError(error)))
            },
            offset
        )
    }

}