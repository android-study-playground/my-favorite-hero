package com.br.myfavoritehero.util

import com.br.myfavoritehero.util.Constants.landscape_amazing
import com.br.myfavoritehero.util.Constants.portrait_fantastic
import com.br.myfavoritehero.util.Constants.separator
import com.br.myfavoritehero.util.Constants.type

fun String.getLargePortraitThumbnail(): String {
    return "$this$separator$portrait_fantastic$type"
}

fun String.getLargeLandscapeThumbnail(): String {
    return "$this$separator$landscape_amazing$type"
}

fun String.getLargeThumbnail(): String {
    return "$this$separator$landscape_amazing$type"
}