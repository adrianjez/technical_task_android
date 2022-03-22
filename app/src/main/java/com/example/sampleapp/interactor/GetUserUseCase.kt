package com.example.sampleapp.interactor

import com.example.sampleapp.data.repository.IUserRepository
import com.example.sampleapp.data.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repository: IUserRepository) {
  suspend fun getUsers() = repository.getUsers()
}