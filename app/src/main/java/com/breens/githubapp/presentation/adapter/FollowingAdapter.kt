package com.breens.githubapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.breens.githubapp.databinding.ItemFollowersRecordBinding
import com.breens.githubapp.domain.models.Following

class FollowingAdapter : RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>() {

    inner class FollowingViewHolder(val binding: ItemFollowersRecordBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<Following>() {
        override fun areItemsTheSame(
            oldItem: Following,
            newItem: Following
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Following,
            newItem: Following
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        val itemRecordBinding = ItemFollowersRecordBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FollowingViewHolder(itemRecordBinding)
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        val currentItem = differ.currentList[position]
        holder.binding.apply {
            userProfile.load(currentItem.avatarUrl) {
                crossfade(false)
                transformations(CircleCropTransformation())
            }
            ("Username: " + currentItem.login).also { name.text = it }
        }
    }

    override fun getItemCount() = differ.currentList.size
}