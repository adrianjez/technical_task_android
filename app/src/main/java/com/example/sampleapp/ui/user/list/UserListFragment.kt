package com.example.sampleapp.ui.user.list

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.sampleapp.R
import com.example.sampleapp.databinding.FragmentUserListBinding
import com.example.sampleapp.extensions.displayQuestion
import com.example.sampleapp.ui.base.observe
import com.example.sampleapp.ui.base.runOnce
import com.example.sampleapp.ui.user.create.UserCreateDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment(R.layout.fragment_user_list) {

  private val viewModel by viewModels<UserListViewModel>()

  private var _binding: FragmentUserListBinding? = null
  private val binding get() = _binding!!

  private var adapter: UserListAdapter = UserListAdapter {
    displayQuestion(
      R.string.question_delete_user_confirm,
      onConfirmCallback = { viewModel.removeUserAt(it)}
    )
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setHasOptionsMenu(true)
    viewModel.runOnce { it.initUserList() }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    _binding = FragmentUserListBinding.bind(view)
    binding.apply {
      recyclerView.adapter = adapter
    }
    observe(viewModel.isLoadingDisplayed) {
      binding.progress.isVisible = it
    }
    observe(viewModel.getViewEntityLiveData()) {
      adapter.submitList(it.users)
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.user_list_menu, menu)
    super.onCreateOptionsMenu(menu, inflater)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
    R.id.menu_action_add -> {
      UserCreateDialogFragment().also {
        it.onSuccessCallback = { viewModel.initUserList() }
        it.show(childFragmentManager, this.javaClass.name)
      }
      true
    }
    else -> {false}
  }
}