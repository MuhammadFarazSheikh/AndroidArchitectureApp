package com.androidengineer.androidarchitectureapp.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.androidengineer.androidarchitectureapp.databinding.ActivityMainBinding
import com.androidengineer.androidarchitectureapp.presentation.recyclerviewadapter.PostsRecyclerViewAdapter
import com.androidengineer.androidarchitectureapp.presentation.viewmodels.PostsUiState
import com.androidengineer.androidarchitectureapp.presentation.viewmodels.PostsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var adapter: PostsRecyclerViewAdapter
    private val postsViewModel: PostsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        adapter = PostsRecyclerViewAdapter()
        mainBinding.recyclerView.adapter = adapter

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
                            Toast.makeText(this@MainActivity, state.message, Toast.LENGTH_SHORT).show()
                        }
                        is PostsUiState.Loading -> {
                            Toast.makeText(this@MainActivity, "Loading", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}