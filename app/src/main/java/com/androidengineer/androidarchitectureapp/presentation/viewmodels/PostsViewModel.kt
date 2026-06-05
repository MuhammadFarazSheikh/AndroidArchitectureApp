package com.androidengineer.androidarchitectureapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidengineer.androidarchitectureapp.data.repository.PostsRepository
import com.androidengineer.androidarchitectureapp.presentation.model.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.androidengineer.androidarchitectureapp.data.repository.Result
import com.androidengineer.androidarchitectureapp.data.repository.asResult

class PostsViewModel(
    private val repository: PostsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<PostsUiState>(PostsUiState.Loading)
    val uiState: StateFlow<PostsUiState> = _uiState.asStateFlow()

    init {
        loadPosts()
    }

    private fun loadPosts() {
        viewModelScope.launch {
            repository().asResult().collect { result ->
                when (result) {
                    is Result.Success -> _uiState.value = PostsUiState.Success(result.data)
                    is Result.Error -> _uiState.value = PostsUiState.Error(result.exception.message!!)
                    is Result.Loading ->  _uiState.value = PostsUiState.Loading
                }
            }
        }
    }
}

sealed interface PostsUiState {
    data object Loading : PostsUiState
    data class Success(val posts: List<Post>) : PostsUiState
    data class Error(val message: String) : PostsUiState
}
