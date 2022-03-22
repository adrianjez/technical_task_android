package com.example.sampleapp.ui.user.create

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.sampleapp.R
import com.example.sampleapp.databinding.FragmentUserCreateBinding
import com.example.sampleapp.extensions.hideKeyboard
import com.example.sampleapp.ui.base.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserCreateDialogFragment : DialogFragment(R.layout.fragment_user_create) {

  private val viewModel by viewModels<UserCreateViewModel>()

  private var _binding: FragmentUserCreateBinding? = null
  private val binding get() = _binding!!

  lateinit var onSuccessCallback: (() -> Unit)

  override fun onStart() {
    super.onStart()
    val dialog: Dialog? = dialog
    if (dialog != null) {
      val width = ViewGroup.LayoutParams.MATCH_PARENT
      val height = ViewGroup.LayoutParams.WRAP_CONTENT
      dialog.window?.setLayout(width, height)
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    _binding = FragmentUserCreateBinding.bind(view)
    initView()
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  private fun initView() = with(binding) {
    observe(viewModel.isLoadingDisplayed) {
      progress.isVisible = it
    }
    observe(viewModel.getProcessFinishedLiveData()) { isFinished ->
      if (isFinished) {
        dismiss()
        onSuccessCallback()
      }
    }
    addButton.setOnClickListener {
      hideKeyboard(from = it)
      viewModel.addUser(
        name = binding.nameInput.text.toString(),
        email = binding.emailInput.text.toString(),
        isActive = binding.activeSwitch.isChecked,
        isMale = binding.genderRowIdContainer.checkedRadioButtonId == R.id.radio_male
      )
    }
  }
}