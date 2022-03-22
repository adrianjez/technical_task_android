package com.example.sampleapp.data.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

open class BaseRepository {

  protected suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
  ): ResultWrapper<T> {
    return withContext(dispatcher) {
      try {
        ResultWrapper.Success(apiCall.invoke())
      } catch (throwable: Throwable) {
        throwable.printStackTrace()
        when (throwable) {
          is IOException -> ResultWrapper.NetworkError
          is HttpException -> {
            val code = throwable.code()
            ResultWrapper.GenericError(code, "Http Exception with code: $code")
          }
          else -> {
            ResultWrapper.GenericError(null, "Generic error")
          }
        }
      }
    }
  }
}