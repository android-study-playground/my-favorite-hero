package com.br.myfavoritehero.features.listCharacter

import androidx.lifecycle.MutableLiveData
import com.br.myfavoritehero.base.BaseViewModel
import com.br.myfavoritehero.data.models.Hero
import com.br.myfavoritehero.data.models.ViewStateModel
import com.br.myfavoritehero.data.data.DataBaseAccess

class ListHeroesViewModel: BaseViewModel() {

    private val repository: DataBaseAccess = DataBaseAccess()

    private val viewStateResponse: MutableLiveData<ViewStateModel<ArrayList<Hero>>> = MutableLiveData()
    private val viewStateResponseLoadMore: MutableLiveData<ViewStateModel<ArrayList<Hero>>> = MutableLiveData()


    fun getHeroes() = viewStateResponse
    fun getMore() = viewStateResponseLoadMore

    fun loadHeroes(){
        viewStateResponse.postValue(ViewStateModel(ViewStateModel.Status.LOADING))
        repository.getHeroes(
            { list ->
                viewStateResponse.postValue(ViewStateModel(status = ViewStateModel.Status.SUCCESS, model = list))
            }, {error ->
                viewStateResponse.postValue(ViewStateModel(status = ViewStateModel.Status.ERROR, errors = notKnownError(error)))
            }
        )
    }

    fun loadMore(){
        viewStateResponseLoadMore.postValue(ViewStateModel(ViewStateModel.Status.LOADING))
        repository.loadMore(
            { list ->
                viewStateResponseLoadMore.postValue(ViewStateModel(status = ViewStateModel.Status.SUCCESS, model = list))
            }, {error ->
                viewStateResponseLoadMore.postValue(ViewStateModel(status = ViewStateModel.Status.ERROR, errors = notKnownError(error)))
            }
        )
    }

}