package com.example.sampleapp.extensions

import android.app.Activity
import android.app.Dialog
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.sampleapp.R

fun Fragment.displayQuestion(@StringRes titleResId: Int, onConfirmCallback: (() -> Unit) = {}, onDeclineCallback: (() -> Unit) = {}) {
  lateinit var dialog: Dialog
  val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
  builder
    .setMessage(resources.getString(titleResId))
    .setPositiveButton(resources.getString(R.string.action_yes)) { _, _ ->
      dialog.dismiss()
      onConfirmCallback()
    }
    .setNegativeButton(R.string.action_no) { _, _ ->
      dialog.dismiss()
      onDeclineCallback()
    }

  dialog = builder.create()
  dialog.show()
}

fun Fragment.hideKeyboard(from: View) {
  with(requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager) {
    hideSoftInputFromWindow(from.windowToken, 0)
  }
}