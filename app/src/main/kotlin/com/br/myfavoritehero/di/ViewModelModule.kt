package com.br.myfavoritehero.di

import com.br.myfavoritehero.features.listCharacter.ListHeroesViewModel
import com.br.myfavoritehero.features.listComics.ComicsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ListHeroesViewModel(get()) }
    viewModel { ComicsViewModel(get()) }
}