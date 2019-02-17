package com.br.myfavoritehero.data.interfaces

import com.br.myfavoritehero.data.models.Hero


interface HeroEventListener{
    fun onHeroClicked(hero: Hero)
}