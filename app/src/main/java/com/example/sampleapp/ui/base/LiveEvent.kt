package com.example.sampleapp.ui.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

interface LiveEvent<T : Any> {
  val value: T
  fun observe(viewLifecycleOwner: LifecycleOwner, eventObserver: (T) -> Unit)
  fun removeObservers(viewLifecycleOwner: LifecycleOwner)
}

fun <T : Any> Fragment.observe(liveEvents: LiveEvent<T>, observer: (T) -> Unit) =
  liveEvents.observe(viewLifecycleOwner) { observer(it) }
