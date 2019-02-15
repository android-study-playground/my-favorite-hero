package com.br.myfavoritehero.data.models

import java.io.Serializable

data class Item(
    val resourceURI: String = String(),
    val name: String = String(),
    val type: String = String()
): Serializable