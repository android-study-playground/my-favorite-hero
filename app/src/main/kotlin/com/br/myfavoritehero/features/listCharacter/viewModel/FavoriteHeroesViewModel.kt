package com.br.myfavoritehero.features.listCharacter.viewModel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.br.myfavoritehero.base.BaseViewModel
import com.br.myfavoritehero.data.dao.HeroDao
import com.br.myfavoritehero.data.models.Hero

class FavoriteHeroesViewModel(private val heroDao: HeroDao): BaseViewModel() {

    private val favoriteHeroesData: MutableLiveData<ArrayList<Hero>> = MutableLiveData()

    fun getFavoriteHeroes() = favoriteHeroesData

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun loadFavoriteHeroes(){
        heroDao.getFavorites().observeForever {
            favoriteHeroesData.postValue(it as ArrayList<Hero>?)
        }
    }

}