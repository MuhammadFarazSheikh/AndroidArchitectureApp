package com.androidengineer.core.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import retrofit2.HttpException
import java.io.IOException

sealed interface Result<out T> {
    data class Success<out T>(val data: T) : Result<T>
    data class Error(val exception: Throwable) : Result<Nothing>
    data object Loading : Result<Nothing>
}

/**
 * Custom Exception to handle API specific errors with status codes.
 */
data class ApiException(
    val code: Int,
    override val message: String?,
    val throwable: Throwable? = null
) : Exception(message, throwable)

/**
 * Extension to convert a Flow into a Result flow, with generic error handling
 * for network and API status codes.
 */
fun <T> Flow<T>.asResult(): Flow<Result<T>> = map<T, Result<T>> { result -> 
    Result.Success(result) 
}
.onStart { emit(Result.Loading) }
.catch { error ->
    val mappedError = when (error) {
        is HttpException -> {
            // Handle specific status codes here or wrap them
            ApiException(
                code = error.code(),
                message = error.message(),
                throwable = error
            )
        }
        is IOException -> {
            // Handle No Internet / Timeout cases
            Exception("Network connection error", error)
        }
        else -> error
    }
    emit(Result.Error(mappedError))
}
