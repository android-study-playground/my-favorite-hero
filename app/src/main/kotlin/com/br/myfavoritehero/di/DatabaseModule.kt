package com.br.myfavoritehero.di

import androidx.room.Room
import com.br.myfavoritehero.data.database.AppDatabase
import org.koin.dsl.module.module

val databaseModule = module {

    // Room Database
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "my-favorite-hero-db").allowMainThreadQueries().build()
    }

    // heroDAO
    single { get<AppDatabase>().heroDao() }

}
