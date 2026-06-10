package com.androidengineer.feature.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidengineer.feature.model.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.androidengineer.core.data.repository.PostsRepository
import com.androidengineer.core.data.repository.asResult
import com.androidengineer.core.data.repository.Result
import com.androidengineer.feature.remoteToPresentationModel

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
                    is Result.Success -> _uiState.value = PostsUiState.Success(result.data.map { post -> post.remoteToPresentationModel() })
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
