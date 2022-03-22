package com.example.sampleapp.ui.base

import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

open class LiveData<T : Any> : androidx.lifecycle.LiveData<T>() {

  var isInitialized = false
    protected set

  override fun getValue(): T {
    return super.getValue()!!
  }

  fun observe(viewLifecycleOwner: LifecycleOwner, observer: (T) -> Unit) {
    super.observe(viewLifecycleOwner) { observer(it) }
  }
}

fun <T : Any> Fragment.observe(liveData: LiveData<T>, observer: (T) -> Unit) =
  liveData.observe(viewLifecycleOwner) { observer(it) }
