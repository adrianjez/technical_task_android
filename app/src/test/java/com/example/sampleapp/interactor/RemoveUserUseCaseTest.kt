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
class RemoveUserUseCaseTest {

  @get:Rule
  var coroutinesTestRule = CoroutineTestRule()

  private lateinit var removeUserUseCase: RemoveUserUseCase

  @Mock
  private lateinit var repository: IUserRepository

  @Before
  fun setUp() {
    removeUserUseCase = RemoveUserUseCase(repository)
  }

  @Test
  fun testUserRemove() = runBlockingTest {
    val testUserID = 1L
    removeUserUseCase.removeUser(testUserID)
    Mockito.verify(repository).deleteUser(testUserID)
    Mockito.verifyNoMoreInteractions(repository)
  }
}