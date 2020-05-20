package com.example.cookingbook.network

sealed class ResultWrapper<out T>{
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class Failure(val code: Int? = null, val message: String): ResultWrapper<Nothing>()
    data class NetworkError(val throwable: Throwable): ResultWrapper<Nothing>()
}

fun <T> ResultWrapper<T>.handleResponse(
    doOnSuccess: (T) -> Unit,
    doOnFailure: (Int?) -> Unit,
    doOnError: () -> Unit
) {
    when (this) {
        is ResultWrapper.Success -> doOnSuccess.invoke(this.value)
        is ResultWrapper.Failure -> doOnFailure.invoke(this.code)
        is ResultWrapper.NetworkError -> doOnError.invoke()
    }
}