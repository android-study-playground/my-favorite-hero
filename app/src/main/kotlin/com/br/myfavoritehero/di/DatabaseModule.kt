package com.br.myfavoritehero.di

import androidx.room.Room
import com.br.myfavoritehero.data.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    // Room Database
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "my-favorite-hero-db")
            .allowMainThreadQueries().build()
    }

    // heroDAO
    single { get<AppDatabase>().heroDao() }

}
