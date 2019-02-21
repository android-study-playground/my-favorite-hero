package com.br.myfavoritehero.data.models

data class ViewStateModel<T> (
    val status: Status,
    val model: T? = null,
    val errors: ErrorResponse? = null
) {
    enum class Status {
        LOADING, SUCCESS, ERROR
    }
}
