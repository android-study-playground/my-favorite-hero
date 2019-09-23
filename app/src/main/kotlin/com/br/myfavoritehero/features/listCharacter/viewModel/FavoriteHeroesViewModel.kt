package com.br.myfavoritehero.features.listCharacter.viewModel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.br.myfavoritehero.base.BaseViewModel
import com.br.myfavoritehero.data.models.Hero
import com.br.myfavoritehero.data.repository.RepositoryContract

class FavoriteHeroesViewModel(private val repository: RepositoryContract) : BaseViewModel() {

    private val favoriteHeroesData: MutableLiveData<ArrayList<Hero>> = MutableLiveData()

    fun updateHero(hero: Hero) = repository.updateHero(hero)

    fun getFavoriteHeroes() = favoriteHeroesData

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun loadFavoriteHeroes() {
        repository.getFavoriteHeroes().observeForever {
            favoriteHeroesData.postValue(it as ArrayList<Hero>?)
        }
    }

}