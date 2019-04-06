package com.br.myfavoritehero.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.br.myfavoritehero.R
import com.br.myfavoritehero.data.models.ErrorResponse
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection

abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    protected val disposables = CompositeDisposable()

    protected fun notKnownError(error: Throwable): ErrorResponse {

        val errorResponse = ErrorResponse()
        when (error) {
            is HttpException -> {
                when (error.code()) {
                    HttpURLConnection.HTTP_BAD_REQUEST -> {
                        errorResponse.messageInt.add(R.string.unknow_error)
                    }
                    HttpURLConnection.HTTP_UNAUTHORIZED -> {
                        errorResponse.messageInt.add(R.string.unauthorized)
                    }
                    else -> errorResponse.messageInt.add(R.string.unknow_error)
                }
            }
            is IOException -> {
                errorResponse.messageInt.add(R.string.no_internet_connection)
            }
        }

        return errorResponse
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

}