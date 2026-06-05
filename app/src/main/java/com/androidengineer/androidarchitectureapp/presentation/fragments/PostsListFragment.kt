package com.androidengineer.androidarchitectureapp.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.androidengineer.androidarchitectureapp.databinding.FragmentPostsListBinding
import com.androidengineer.androidarchitectureapp.presentation.interfaces.OnItemClick
import com.androidengineer.androidarchitectureapp.presentation.model.Post
import com.androidengineer.androidarchitectureapp.presentation.recyclerviewadapter.PostsRecyclerViewAdapter
import com.androidengineer.androidarchitectureapp.presentation.viewmodels.PostsUiState
import com.androidengineer.androidarchitectureapp.presentation.viewmodels.PostsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class PostsListFragment : Fragment(), OnItemClick {

    private lateinit var binding: FragmentPostsListBinding
    private lateinit var adapter: PostsRecyclerViewAdapter
    private val postsViewModel: PostsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostsListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PostsRecyclerViewAdapter(this)
        binding.recyclerView.adapter = adapter

        observePosts()
    }

    private fun observePosts() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                postsViewModel.uiState.collect { state ->
                    when (state) {
                        is PostsUiState.Success -> {
                            adapter.submitList(state.posts)
                        }
                        is PostsUiState.Error -> {
                            Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                        }
                        is PostsUiState.Loading -> {
                            Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    override fun onItemClick(item: Post) {
        findNavController().navigate(PostsListFragmentDirections.postListFragmentToPostDetailsFragment(item))
    }
}