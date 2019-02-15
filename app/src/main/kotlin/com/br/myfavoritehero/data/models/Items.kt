package com.br.myfavoritehero.data.models

import java.io.Serializable

data class Items(

    val available: Int = 0,
    val collectionURI: String = String(),
    val items: ArrayList<Item> = ArrayList()

): Serializable