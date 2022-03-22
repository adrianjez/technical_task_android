package com.example.sampleapp.data.repository

import com.example.sampleapp.api.GorestApi
import com.example.sampleapp.data.base.BaseRepository
import com.example.sampleapp.data.mappers.toDomain
import com.example.sampleapp.data.model.CreateUserRequestData
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val gorestApi: GorestApi) : IUserRepository, BaseRepository() {
  override suspend fun getUsers() = safeApiCall(Dispatchers.IO) { gorestApi.getUsers().map { it.toDomain() } }
  override suspend fun createUser(createUserData: CreateUserRequestData) = safeApiCall(Dispatchers.IO) {
    gorestApi.createUser(createUserData).toDomain()
  }
  override suspend fun deleteUser(deleteUserId: Long) = safeApiCall(Dispatchers.IO) {
    gorestApi.deleteUser(deleteUserId)
  }
}