package com.breens.githubapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.breens.githubapp.databinding.ItemRepositoriesRecordBinding
import com.breens.githubapp.domain.models.Repository

class RepositoriesAdapter : RecyclerView.Adapter<RepositoriesAdapter.RepositoriesViewHolder>() {

    inner class RepositoriesViewHolder(val binding: ItemRepositoriesRecordBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<Repository>() {
        override fun areItemsTheSame(
            oldItem: Repository,
            newItem: Repository
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Repository,
            newItem: Repository
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesViewHolder {
        val itemRecordBinding = ItemRepositoriesRecordBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RepositoriesViewHolder(itemRecordBinding)
    }

    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) {
        val currentItem = differ.currentList[position]
        holder.binding.apply {
            reposName.text = currentItem.fullName
            forks.text = currentItem.forksCount.toString()
            issues.text = currentItem.openIssues.toString()
            watches.text = currentItem.watchersCount.toString()
        }
    }

    override fun getItemCount() = differ.currentList.size
}