package com.br.myfavoritehero.data.models

import android.os.Parcelable
import com.br.myfavoritehero.util.Constants
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Hero(

    val id : Int = 0,
    val name : String = String(),
    val description : String = String(),
    val modified : String = String(),
    val thumbnail: Thumbnail = Thumbnail(),
    val resourceURI : String = String(),
    val comics : Items = Items(),
    val series : Items = Items(),
    val stories : Items = Items(),
    val events : Items = Items(),
    val urls : ArrayList<Url> = ArrayList()

): Parcelable