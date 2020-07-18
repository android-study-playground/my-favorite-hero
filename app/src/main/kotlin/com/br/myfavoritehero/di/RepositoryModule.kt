package com.br.myfavoritehero.di

import com.br.myfavoritehero.data.repository.*
import org.koin.dsl.module

val repositoryModule = module {
    single { RepositoryRemote(get()) as RepositoryRemoteContract}
    single { RepositoryLocal(get()) as RepositoryLocalContract }
    single { Repository(get(), get()) as RepositoryContract }
}