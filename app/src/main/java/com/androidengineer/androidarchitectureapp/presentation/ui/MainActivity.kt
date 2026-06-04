package com.androidengineer.androidarchitectureapp.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.androidengineer.androidarchitectureapp.R
import com.androidengineer.androidarchitectureapp.presentation.viewmodels.PostsUiState
import com.androidengineer.androidarchitectureapp.presentation.viewmodels.PostsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val postsViewModel: PostsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observePosts()
    }

    private fun observePosts() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                postsViewModel.uiState.collect { state ->
                    when (state) {
                        is PostsUiState.Success -> {
                            state.posts.forEach { item ->
                                Log.i("TAG", "observePosts: ${item.title}")
                            }
                        }
                        is PostsUiState.Error -> {
                        }
                        is PostsUiState.Loading -> {
                            Log.i("TAG", "observePosts: Loading")
                        }
                    }
                }
            }
        }
    }
}