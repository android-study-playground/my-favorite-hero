package com.br.myfavoritehero.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Url(

    val type : String = String(),
    val url: String = String()

): Parcelable