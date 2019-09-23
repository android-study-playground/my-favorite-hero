package com.br.myfavoritehero.features.heroDetails.viewModel

import com.br.myfavoritehero.base.BaseViewModel
import com.br.myfavoritehero.data.models.Hero
import com.br.myfavoritehero.data.repository.RepositoryContract

class HeroDetailViewModel(private val repository: RepositoryContract) : BaseViewModel() {

    fun getHero(id: Int): Hero = repository.getHero(id)
    fun updateHero(hero: Hero) = repository.updateHero(hero)

}
