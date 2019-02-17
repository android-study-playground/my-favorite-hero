package com.br.myfavoritehero.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Thumbnail(

    val path: String = String(),
    val extension: String = String()

): Parcelable