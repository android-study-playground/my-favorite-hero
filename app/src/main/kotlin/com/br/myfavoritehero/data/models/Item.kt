package com.br.myfavoritehero.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    val resourceURI: String = String(),
    val name: String = String(),
    val type: String = String()
) : Parcelable