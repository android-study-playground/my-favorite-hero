package com.br.myfavoritehero.data.models

data class Data<T>(

    val offset : Int = 0,
    val limit : Int = 0,
    val total : Int = 0,
    val count : Int = 0,
    val results : ArrayList<T> = ArrayList()

)