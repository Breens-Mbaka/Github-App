package com.breens.githubapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.breens.githubapp.databinding.ItemFollowersRecordBinding
import com.breens.githubapp.domain.models.Followers

class FollowersAdapter : RecyclerView.Adapter<FollowersAdapter.FollowersViewHolder>() {

    inner class FollowersViewHolder(val binding: ItemFollowersRecordBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<Followers>() {
        override fun areItemsTheSame(
            oldItem: Followers,
            newItem: Followers
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Followers,
            newItem: Followers
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowersViewHolder {
        val itemRecordBinding = ItemFollowersRecordBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FollowersViewHolder(itemRecordBinding)
    }

    override fun onBindViewHolder(holder: FollowersViewHolder, position: Int) {
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