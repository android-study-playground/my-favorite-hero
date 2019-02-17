package com.br.myfavoritehero.di

import com.br.myfavoritehero.data.request.Repository
import org.koin.dsl.module.module

val repositoryModule = module {

    factory { Repository(get()) }

}