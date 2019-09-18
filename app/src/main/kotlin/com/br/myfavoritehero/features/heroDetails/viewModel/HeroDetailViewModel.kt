package com.br.myfavoritehero.features.heroDetails.viewModel

import com.br.myfavoritehero.base.BaseViewModel
import com.br.myfavoritehero.data.dao.HeroDao
import com.br.myfavoritehero.data.models.Hero

class HeroDetailViewModel(private val heroDao : HeroDao): BaseViewModel() {

    fun getHero(id:Int):Hero = heroDao.get(id)
    fun updateHero(hero: Hero) = heroDao.update(hero)

}
