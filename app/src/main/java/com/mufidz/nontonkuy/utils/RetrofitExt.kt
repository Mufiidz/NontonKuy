package com.mufidz.nontonkuy.utils

import com.mufidz.nontonkuy.data.RetrofitResult
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.coroutines.resume

suspend fun <T : Any> Call<T>.awaitResult(): RetrofitResult<T> {
    return suspendCancellableCoroutine { continuation ->
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                continuation.resumeWith(runCatching {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body == null) {
                            RetrofitResult.Exception(NullPointerException("Response body is null"))
                        } else {
                            RetrofitResult.Success(body)
                        }
                    } else {
                        RetrofitResult.Error(HttpException(response))
                    }
                })
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                if (continuation.isCancelled) return
                continuation.resume(RetrofitResult.Exception(mapToNetworkError(t)))
            }
        })

        continuation.invokeOnCancellation { runCatching { cancel() } }
    }
}

private fun mapToNetworkError(t: Throwable): Exception {
    return when(t){
        is SocketTimeoutException -> SocketTimeoutException("Connection Timed Out")
        is UnknownHostException -> NoInternetException()
        else -> UnKnownException()
    }
}

class NoInternetException : IOException() {
    override val message: String
        get() = "No Internet Connection"
}

class UnKnownException: Exception() {
    override val message: String
        get() = "Some Unknown Error Occurred"
}
