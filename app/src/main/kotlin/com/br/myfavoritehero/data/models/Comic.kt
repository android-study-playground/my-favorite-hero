package com.br.myfavoritehero.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comic(
    val id : Int = 0,
    val digitalId : Int = 0,
    val title : String = String(),
    val description : String = String(),
    val modified : String = String(),
    val thumbnail: Thumbnail = Thumbnail(),
    val resourceURI : String = String(),
    val creators : Items = Items(),
    val characters : Items = Items(),
    val series : Items = Items(),
    val stories : Items = Items(),
    val events : Items = Items(),
    val urls : ArrayList<Url> = ArrayList()
): Parcelable