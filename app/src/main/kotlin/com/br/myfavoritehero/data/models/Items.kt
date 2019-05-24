package com.br.myfavoritehero.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Items(
    val available: Int = 0,
    val collectionURI: String = String(),
    val items: ArrayList<Item> = ArrayList()
) : Parcelable