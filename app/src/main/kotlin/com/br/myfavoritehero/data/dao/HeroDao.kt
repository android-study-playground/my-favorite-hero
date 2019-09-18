package com.br.myfavoritehero.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.br.myfavoritehero.data.models.Hero

@Dao
interface HeroDao {

    @Query("SELECT * FROM hero WHERE hero.isFavorite = 1 ORDER BY hero.name") fun getFavorites(): LiveData<List<Hero>>

    @Query("SELECT * FROM hero ORDER BY hero.name") fun getAll(): LiveData<List<Hero>>

    @Query("SELECT * FROM hero ORDER BY hero.name LIMIT :limit OFFSET :offset ") fun getAll(limit:Int ,offset: Int): LiveData<List<Hero>>

    @Query("SELECT * FROM hero WHERE hero.id = :id ") fun get(id:Int): Hero

    @Insert(onConflict = OnConflictStrategy.IGNORE) fun saveAll(entities: ArrayList<Hero>)

    @Insert(onConflict = OnConflictStrategy.IGNORE) fun save(hero: Hero): Long

    @Update fun update(hero: Hero)

    @Delete fun delete(hero: Hero)
}