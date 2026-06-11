package com.androidengineer.feature.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.androidengineer.core.domain.model.Post
import com.androidengineer.feature.adapter.PostsListRecyclerViewAdapter
import com.androidengineer.feature.databinding.FragmentPostsListBinding
import com.androidengineer.feature.interfaces.OnItemClick
import com.androidengineer.feature.viewmodels.PostsUiState
import com.androidengineer.feature.viewmodels.PostsViewModel
import kotlinx.coroutines.launch

class PostsListFragment : Fragment(), OnItemClick {

    private val viewModel: PostsViewModel by viewModel()
    private lateinit var adapter: PostsListRecyclerViewAdapter

    private lateinit var binding: FragmentPostsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostsListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PostsListRecyclerViewAdapter(this)
        binding.postsRecyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                when(uiState) {
                    is PostsUiState.Success -> adapter.submitList(uiState.posts)
                    is PostsUiState.Error -> {}
                    is PostsUiState.Loading -> {}
                }
            }
        }
    }

    override fun onItemClick(item: Post) {
        findNavController().navigate(PostsListFragmentDirections.actionPostsListFragmentToPostsDetailFragment(item))
    }
}