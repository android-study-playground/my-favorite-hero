package com.br.myfavoritehero.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.br.myfavoritehero.data.models.Hero

@Dao
interface HeroDao {

    @Query("SELECT * FROM hero where hero.isFavorite = 1") fun getFavorites(): LiveData<List<Hero>>

    @Query("SELECT * FROM hero") fun getAll(): LiveData<List<Hero>>

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun saveAll(entities: ArrayList<Hero>)

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun save(hero: Hero): Long

    @Update fun update(hero: Hero)

    @Delete fun delete(hero: Hero)
}
