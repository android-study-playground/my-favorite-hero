package com.br.myfavoritehero.util

import com.br.myfavoritehero.util.Constants.landscape_amazing
import com.br.myfavoritehero.util.Constants.portrait_fantastic
import com.br.myfavoritehero.util.Constants.separator
import com.br.myfavoritehero.util.Constants.type
import com.br.myfavoritehero.util.Constants.HTTPS_STRING
import com.br.myfavoritehero.util.Constants.HTTP_STRING

fun String.getLargePortraitThumbnail(): String {
    return "${this.replace(HTTP_STRING, HTTPS_STRING)}$separator$portrait_fantastic$type"
}

fun String.getLargeLandscapeThumbnail(): String {
    return "${this.replace(HTTP_STRING, HTTPS_STRING)}$separator$landscape_amazing$type"
}

fun String.getLargeThumbnail(): String {
    return "${this.replace(HTTP_STRING, HTTPS_STRING)}$separator$landscape_amazing$type"
}