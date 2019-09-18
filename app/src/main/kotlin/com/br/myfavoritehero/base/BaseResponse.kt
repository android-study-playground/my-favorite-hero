package com.br.myfavoritehero.base

import com.br.myfavoritehero.data.models.Data

data class BaseResponse<T>(

    val code: Int = 0,
    val status: String = String(),
    val copyright: String = String(),
    val attributionText: String = String(),
    val attributionHTML: String = String(),
    val data: Data<T> = Data(),
    val etag: String = String()

)