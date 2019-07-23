package com.br.myfavoritehero.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.br.myfavoritehero.R
import com.br.myfavoritehero.data.models.ErrorResponse
import com.br.myfavoritehero.util.Constants.ERROR_NOT_CONNECTED
import com.br.myfavoritehero.util.Constants.MESSAGE_ERROR_NOT_CONNECTED
import com.br.myfavoritehero.util.Constants.MESSAGE_ERROR_TIME_OUT
import com.google.gson.GsonBuilder
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException

abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    protected val disposables = CompositeDisposable()
    private val gson = GsonBuilder().create()

    protected fun errorHandler(error: Throwable): ErrorResponse {

        var errorResponse = ErrorResponse()

        when (error) {
            is HttpException -> {
                errorResponse = gson.fromJson(
                        error.response().errorBody()?.charStream(),
                        ErrorResponse::class.java)

                when (error.code()) {
                    HttpURLConnection.HTTP_CONFLICT -> {
                        errorResponse.extra = R.string.unknow_error
                    }
                    HttpURLConnection.HTTP_UNAUTHORIZED -> {
                        errorResponse.extra = R.string.unauthorized
                    }
                    HttpURLConnection.HTTP_NOT_FOUND -> {
                        errorResponse.extra = R.string.unknow_error
                    }
                    HttpURLConnection.HTTP_BAD_METHOD -> {
                        errorResponse.extra = R.string.unknow_error
                    }
                    HttpURLConnection.HTTP_INTERNAL_ERROR -> {
                        errorResponse.extra = R.string.server_down
                    }
                    else -> {
                        errorResponse.extra = R.string.unknow_error
                    }
                }
            }
            is SocketTimeoutException -> {
                errorResponse.code = HttpURLConnection.HTTP_CLIENT_TIMEOUT
                errorResponse.status = MESSAGE_ERROR_TIME_OUT
                errorResponse.message = MESSAGE_ERROR_TIME_OUT
                errorResponse.extra = R.string.server_down
            }
            is IOException -> {
                errorResponse.code = ERROR_NOT_CONNECTED
                errorResponse.status = MESSAGE_ERROR_NOT_CONNECTED
                errorResponse.message = MESSAGE_ERROR_NOT_CONNECTED
                errorResponse.extra = R.string.no_internet_connection
            }
        }

        return errorResponse
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}