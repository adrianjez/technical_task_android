package com.example.sampleapp.ui.user.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope

import com.example.sampleapp.interactor.GetUserUseCase
import com.example.sampleapp.interactor.RemoveUserUseCase
import com.example.sampleapp.ui.base.BaseViewModel
import com.example.sampleapp.ui.base.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
  private val getUserUseCase: GetUserUseCase,
  private val removeUserUseCase: RemoveUserUseCase
): BaseViewModel() {

  private val viewEntity = MutableLiveData<UserListViewEntity>()
  fun getViewEntityLiveData() : LiveData<UserListViewEntity> = viewEntity

  fun initUserList() {
    isLoadingDisplayed.value = true
    viewModelScope.launch {
      handleErrorIfNeeded(getUserUseCase.getUsers()) {
        viewEntity.value = UserListViewEntity(it)
      }
    }
  }

  fun removeUserAt(index: Int) {
    isLoadingDisplayed.value = true
    viewModelScope.launch {
      handleErrorIfNeeded(removeUserUseCase.removeUser(viewEntity.value.users[index].id)) {
        initUserList()
      }
    }
  }
}