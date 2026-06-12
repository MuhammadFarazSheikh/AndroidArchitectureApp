package com.androidengineer.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.androidengineer.feature.databinding.PostListItemBinding
import com.androidengineer.feature.interfaces.OnItemClick
import com.androidengineer.feature.model.PostUiModel

class PostsListRecyclerViewAdapter(private val onItemClick: OnItemClick) :
    ListAdapter<PostUiModel, PostsListRecyclerViewAdapter.PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostViewHolder {
        return PostViewHolder(
            PostListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: PostViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    inner class PostViewHolder(private val binding: PostListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(postUiModel: PostUiModel) {
            binding.textViewTitle.text = postUiModel.title
            binding.root.setOnClickListener {
                onItemClick.onItemClick(postUiModel)
            }
        }
    }

    class PostDiffCallback : DiffUtil.ItemCallback<PostUiModel>() {
        override fun areItemsTheSame(oldItem: PostUiModel, newItem: PostUiModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PostUiModel, newItem: PostUiModel): Boolean {
            return oldItem == newItem
        }
    }
}
