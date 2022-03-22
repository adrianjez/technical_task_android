package com.example.sampleapp.data.mappers

import com.example.sampleapp.data.model.ResponseUserData
import com.example.sampleapp.domain.User

fun ResponseUserData.toDomain() = User(
  id, name, email, gender, status
)