package com.br.myfavoritehero.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.br.myfavoritehero.data.dao.HeroDao
import com.br.myfavoritehero.data.models.Hero

@Database(version = 1, entities = [Hero::class])
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun heroDao(): HeroDao
}