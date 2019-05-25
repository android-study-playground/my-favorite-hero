package com.br.myfavoritehero.util

import com.br.myfavoritehero.util.Constants.landscape_amazing
import com.br.myfavoritehero.util.Constants.portrait_fantastic
import com.br.myfavoritehero.util.Constants.separator
import com.br.myfavoritehero.util.Constants.type

fun String.getLargePortraitThumbnail(): String {
    return "${this.replace("http://", "https://")}$separator$portrait_fantastic$type"
}

fun String.getLargeLandscapeThumbnail(): String {
    return "${this.replace("http://", "https://")}$separator$landscape_amazing$type"
}

fun String.getLargeThumbnail(): String {
    return "${this.replace("http://", "https://")}$separator$landscape_amazing$type"
}