package com.example.sampleapp.data.repository

import com.example.sampleapp.data.base.ResultWrapper
import com.example.sampleapp.data.model.CreateUserRequestData
import com.example.sampleapp.domain.User
import retrofit2.Response

interface IUserRepository {
  suspend fun getUsers(): ResultWrapper<List<User>>
  suspend fun createUser(createUserData: CreateUserRequestData): ResultWrapper<User>
  suspend fun deleteUser(deleteUserId: Long): ResultWrapper<Response<Unit>>
}