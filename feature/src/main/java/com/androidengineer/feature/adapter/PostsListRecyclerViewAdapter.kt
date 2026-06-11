package com.androidengineer.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidengineer.core.domain.model.Post
import com.androidengineer.feature.databinding.PostListItemBinding
import com.androidengineer.feature.interfaces.OnItemClick

class PostsListRecyclerViewAdapter(val onItemClick: OnItemClick) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list = arrayListOf<Post>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return PostViewHolder(PostListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val holder = holder as PostViewHolder
        holder.bind(list[position])
        holder.setOnItemClick(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class PostViewHolder(val binding: PostListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.textViewTitle.text = post.title
        }

        fun setOnItemClick(post: Post) {
            binding.root.setOnClickListener {
                onItemClick.onItemClick(post)
            }
        }
    }

    fun submitList(list: List<Post>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}