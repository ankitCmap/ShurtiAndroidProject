package com.lock.the.box.network

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

sealed class Result<out T : Any>() {
    class Failure<T : Any>(val throwable: Exception) : Result<T>()
    class Success<T : Any>(val data: T) : Result<T>()
    //class Unauthorised<Nothing> : Result<Nothing>()
}

suspend fun <T : Any> safeApiCall(dispatcher: CoroutineDispatcher = Dispatchers.IO, apiCall: suspend () -> T): Result<T> {
    return withContext(dispatcher) {
        try {
            Result.Success(apiCall.invoke())
        } catch (exception: Exception) {
            Log.e("NetworkHelper", "error $exception")
            Result.Failure(exception)

        }
    }
}
