package com.br.myfavoritehero.features.listComics

import androidx.lifecycle.MutableLiveData
import com.br.myfavoritehero.base.BaseViewModel
import com.br.myfavoritehero.data.models.Comic
import com.br.myfavoritehero.data.models.ViewStateModel
import com.br.myfavoritehero.data.request.RepositoryContract

class ComicsViewModel(private val repository: RepositoryContract): BaseViewModel() {

    private val viewStateResponse: MutableLiveData<ViewStateModel<ArrayList<Comic>>> = MutableLiveData()

    fun getComics() = viewStateResponse

    fun loadComics(characterId: String){
        viewStateResponse.postValue(ViewStateModel(ViewStateModel.Status.LOADING))
        disposables.add(repository.getComics(
                characterId).subscribe(
                { base ->
                    viewStateResponse.postValue(ViewStateModel(status = ViewStateModel.Status.SUCCESS, model = base.data.results))
                },
                { error ->
                    viewStateResponse.postValue(ViewStateModel(status = ViewStateModel.Status.ERROR, errors = notKnownError(error)))
                }
        ))
    }

}