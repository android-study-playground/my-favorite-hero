package com.br.myfavoritehero.di

import com.br.myfavoritehero.features.listCharacter.ListHeroesViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {

    viewModel { ListHeroesViewModel(get()) }

}