package com.br.myfavoritehero.data.models

data class ErrorResponse(
    var code: Any = Any(),
    var message: String = String(),
    var status: String = String(),
    var extra: Int = 0
) {
    override fun toString(): String {
        return code.toString() + ": " + getErrorMessage()
    }

    fun getErrorMessage(): String {
        var result = when (code is String) {
            true -> message
            false -> status
        }
        if (result == "") {
            result = "Message not found."
        }
        return result
    }
}