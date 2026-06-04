package com.androidengineer.androidarchitectureapp.presentation.recyclerviewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidengineer.androidarchitectureapp.databinding.RecyclerItemBinding
import com.androidengineer.androidarchitectureapp.presentation.model.Post

class PostsRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val postsList = arrayListOf<Post>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return PostsViewHolder(RecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as PostsViewHolder).binding(postsList.get(position))
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    inner class PostsViewHolder(val bind: RecyclerItemBinding) : RecyclerView.ViewHolder(bind.root) {
        fun binding(post: Post) {
            bind.textTitle.text = post.title
        }
    }

    fun submitList(posts: List<Post>) {
        postsList.clear()
        postsList.addAll(posts)
        notifyDataSetChanged()
    }
}