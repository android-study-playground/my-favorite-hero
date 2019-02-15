package com.br.myfavoritehero.features.listCharacter

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.br.myfavoritehero.base.BaseViewModel
import com.br.myfavoritehero.data.models.Hero
import com.br.myfavoritehero.data.models.ViewStateModel
import com.br.myfavoritehero.data.repositories.Repository

class ListHeroesViewModel(private val repository: Repository): BaseViewModel() {

    private val viewStateResponse: MutableLiveData<ViewStateModel<ArrayList<Hero>>> = MutableLiveData()

    fun getHeroes() = viewStateResponse

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun loadHeroes(){
        viewStateResponse.postValue(ViewStateModel(ViewStateModel.Status.LOADING))
        repository.getHeroes(
            { base ->
                viewStateResponse.postValue(ViewStateModel(status = ViewStateModel.Status.SUCCESS, model = base.data.results as ArrayList<Hero>))
            }, {error ->
                viewStateResponse.postValue(ViewStateModel(status = ViewStateModel.Status.ERROR, errors = notKnownError(error)))
            }
        )
    }

}