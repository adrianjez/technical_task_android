package com.example.sampleapp.ui.user.create

import androidx.lifecycle.viewModelScope
import com.example.sampleapp.data.model.CreateUserRequestData
import com.example.sampleapp.interactor.CreateUserUseCase
import com.example.sampleapp.ui.base.BaseViewModel
import com.example.sampleapp.ui.base.LiveEvent
import com.example.sampleapp.ui.base.MutableLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserCreateViewModel @Inject constructor(
  private val createUserUseCase: CreateUserUseCase
) : BaseViewModel() {

  private val processFinish = MutableLiveEvent<Boolean>()
  fun getProcessFinishedLiveData(): LiveEvent<Boolean> = processFinish

  fun addUser(name: String, email: String, isActive: Boolean, isMale: Boolean) {
    isLoadingDisplayed.value = true
    viewModelScope.launch {
      handleErrorIfNeeded(
        createUserUseCase.createUser(
          CreateUserRequestData(
            name = name,
            email = email,
            gender = if (isMale) "male" else "female",
            status = if (isActive) "active" else "inactive"
          )
        )
      ) { processFinish.value = true }
    }
  }
}