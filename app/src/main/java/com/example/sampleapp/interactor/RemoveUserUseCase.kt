package com.example.sampleapp.interactor

import com.example.sampleapp.data.repository.IUserRepository
import com.example.sampleapp.data.repository.UserRepository
import javax.inject.Inject

class RemoveUserUseCase @Inject constructor(private val repository: IUserRepository) {
  suspend fun removeUser(userId: Long) = repository.deleteUser(userId)
}