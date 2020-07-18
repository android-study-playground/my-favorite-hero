package com.br.myfavoritehero.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "hero")
@Parcelize
data class Hero(
        @PrimaryKey var id : Int = 0,
        var name : String = String(),
        var description : String = String(),
        var modified : String = String(),
        val thumbnail: Thumbnail = Thumbnail(),
        var resourceURI : String = String(),
        val comics : Items = Items(),
        val series : Items = Items(),
        val stories : Items = Items(),
        val events : Items = Items(),
        val urls : ArrayList<Url> = ArrayList(),
        var isFavorite : Boolean = false
): Parcelable
