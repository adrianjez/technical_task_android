package com.example.sampleapp.ui.base

open class MutableLiveData<T : Any>() : LiveData<T>() {

  constructor(initialValue: T) : this() {
    value = initialValue
  }

  public override fun postValue(value: T) {
    super.postValue(value)
  }

  public override fun setValue(value: T) {
    isInitialized = true
    super.setValue(value)
  }
}
