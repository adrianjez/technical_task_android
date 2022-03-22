package com.example.sampleapp.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sampleapp.data.base.ResultWrapper

abstract class BaseViewModel : ViewModel() {

  internal var initiated = false
  private val errorMessage = MutableLiveData<String>()
  private val hasError = MutableLiveData(false)

  val isLoadingDisplayed = MutableLiveData(false)

  open fun retry() {}

  protected fun <T> handleErrorIfNeeded(
    resultWrapper: ResultWrapper<T>,
    successCallback: (data: T) -> Unit
  ) {
    when (resultWrapper) {
      is ResultWrapper.NetworkError -> {
        hasError.value = true
        errorMessage.value = "Network error occured"
      }
      is ResultWrapper.GenericError -> {
        hasError.value = true
        errorMessage.value = (resultWrapper.error)
      }
      is ResultWrapper.Success -> {
        hasError.value = false
        successCallback(resultWrapper.value)
      }
    }
    isLoadingDisplayed.postValue(false)
  }

}

fun <T : BaseViewModel> T.runOnce(action: (T) -> Unit) {
  if (!initiated) {
    action(this)
    initiated = true
  }
}