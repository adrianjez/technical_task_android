package com.example.sampleapp.ui.user.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapp.databinding.FragmentUserListItemBinding
import com.example.sampleapp.domain.User

class UserListAdapter(private val onClickAction: (selectedPosition: Int) -> Unit) : ListAdapter<User, UserListAdapter.ViewHolder>(COMPARATOR) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
    ViewHolder(FragmentUserListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    getItem(position)?.let { user ->
      with(holder) {
        bind(onClickAction, user)
      }
    }
  }

  class ViewHolder(private val binding: FragmentUserListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(selectedAction: (selectedPosition: Int) -> Unit, user: User) {
      binding.root.setOnLongClickListener {
        selectedAction.invoke(adapterPosition)
        true
      }
      binding.nameRowIdContainer.text = user.name
      binding.emailRowIdContainer.text = user.email
      binding.statusRowIdContainer.text = user.status
      binding.genderRowIdContainer.text = user.gender
    }
  }

  companion object {
    private val COMPARATOR = object : DiffUtil.ItemCallback<User>() {
      override fun areItemsTheSame(oldItem: User, newItem: User) = oldItem.id == newItem.id
      override fun areContentsTheSame(oldItem: User, newItem: User) = oldItem == newItem
    }
  }
}