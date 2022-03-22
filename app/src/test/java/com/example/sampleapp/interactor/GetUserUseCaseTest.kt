package com.example.sampleapp.interactor

import com.example.sampleapp.base.CoroutineTestRule
import com.example.sampleapp.data.base.ResultWrapper
import com.example.sampleapp.data.mappers.toDomain
import com.example.sampleapp.data.model.CreateUserRequestData
import com.example.sampleapp.data.model.ResponseUserData
import com.example.sampleapp.data.repository.IUserRepository
import com.example.sampleapp.domain.User
import com.nhaarman.mockitokotlin2.doReturn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetUserUseCaseTest {

  @get:Rule
  var coroutinesTestRule = CoroutineTestRule()

  private lateinit var getUserUseCase: GetUserUseCase

  @Mock
  private lateinit var repository: IUserRepository

  @Before
  fun setUp() {
    getUserUseCase = GetUserUseCase(repository)
  }

  @Test
  fun loadingUsers() = runBlockingTest {
    val responseData: ResultWrapper<List<User>> = ResultWrapper.Success(listOf(ResponseUserData(
      id = 1L,
      name = "Test",
      gender = "Male",
      email = "test@gmail.pl",
      status = "active"
    ).toDomain()))
    `when`(repository.getUsers()).thenReturn(responseData)

    val users = getUserUseCase.getUsers()

    assert(users is ResultWrapper.Success)
    with (users as ResultWrapper.Success) {
      assert(value.size == 1)
      with (value.first()){
        assert(id == 1L)
        assert(name == "Test")
        assert(gender == "Male")
        assert(email == "test@gmail.pl")
        assert(status == "active")
      }
    }
  }
}