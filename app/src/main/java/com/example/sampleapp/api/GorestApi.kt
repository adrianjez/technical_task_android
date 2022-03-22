package com.example.sampleapp.api

import com.example.sampleapp.data.model.CreateUserRequestData
import com.example.sampleapp.data.model.ResponseUserData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GorestApi {

  companion object {
    const val BASE_URL = "https://gorest.co.in/public/"
  }

  @GET("v2/users")
  suspend fun getUsers(): List<ResponseUserData>

  @POST("v2/users")
  suspend fun createUser(@Body createUserRequestData: CreateUserRequestData): ResponseUserData

  @DELETE("v2/users/{userId}")
  suspend fun deleteUser(@Path("userId") userId: Long): Response<Unit>
}