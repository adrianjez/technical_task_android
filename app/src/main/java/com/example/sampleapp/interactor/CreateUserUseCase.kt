package com.example.sampleapp.interactor

import com.example.sampleapp.data.repository.UserRepository
import com.example.sampleapp.data.model.CreateUserRequestData
import com.example.sampleapp.data.repository.IUserRepository
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(private val repository: IUserRepository) {
  suspend fun createUser(requestData: CreateUserRequestData) =
    repository.createUser(requestData)
}