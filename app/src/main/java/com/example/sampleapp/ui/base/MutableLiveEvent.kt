package com.example.sampleapp.ui.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import java.util.concurrent.atomic.AtomicBoolean

class MutableLiveEvent<T : Any> : LiveEvent<T> {

  private val liveData = MutableLiveData<Event<T>>()

  var isInitialized = false
    private set

  override var value: T
    set(value) {
      liveData.value = Event(value)
    }
    get() = liveData.value!!.content

  override fun observe(viewLifecycleOwner: LifecycleOwner, eventObserver: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner) { event -> event.getContentIfNotHandled()?.let(eventObserver) }
  }

  override fun removeObservers(viewLifecycleOwner: LifecycleOwner) {
    liveData.removeObservers(viewLifecycleOwner)
  }

  fun postValue(value: T) = liveData.postValue(Event(value))

  private class Event<out T : Any>(val content: T) {

    private var hasBeenHandled = AtomicBoolean(false)

    fun getContentIfNotHandled(): T? {
      return if (hasBeenHandled.compareAndSet(false, true)) content else null
    }
  }
}