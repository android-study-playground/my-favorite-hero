package com.br.myfavoritehero.di

import com.br.myfavoritehero.features.heroDetails.viewModel.HeroDetailViewModel
import com.br.myfavoritehero.features.listCharacter.viewModel.FavoriteHeroesViewModel
import com.br.myfavoritehero.features.listCharacter.viewModel.ListHeroesViewModel
import com.br.myfavoritehero.features.listComics.ComicsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { ListHeroesViewModel(get(), get()) }

    viewModel { FavoriteHeroesViewModel(get()) }

    viewModel { HeroDetailViewModel(get()) }

    viewModel { ComicsViewModel(get()) }
}