package com.example.sampleapp.interactor

import com.example.sampleapp.base.CoroutineTestRule
import com.example.sampleapp.data.model.CreateUserRequestData
import com.example.sampleapp.data.repository.IUserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CreateUserUseCaseTest {

  @get:Rule
  var coroutinesTestRule = CoroutineTestRule()

  private lateinit var createUserUseCase: CreateUserUseCase

  @Mock
  private lateinit var repository: IUserRepository

  @Before
  fun setUp() {
    createUserUseCase = CreateUserUseCase(repository)
  }

  @Test
  fun testUserCreation() = runBlockingTest {
    val requestData = CreateUserRequestData(
      name = "Test",
      gender = "Male",
      email = "test@gmail.pl",
      status = "active"
    )
    createUserUseCase.createUser(requestData)
    Mockito.verify(repository).createUser(requestData)
    Mockito.verifyNoMoreInteractions(repository)
  }
}