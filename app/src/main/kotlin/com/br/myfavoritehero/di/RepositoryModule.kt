package com.br.myfavoritehero.di

import com.br.myfavoritehero.data.request.Repository
import com.br.myfavoritehero.data.request.RepositoryContract
import org.koin.dsl.module.module

val repositoryModule = module {

    factory { Repository(get()) as RepositoryContract}

}