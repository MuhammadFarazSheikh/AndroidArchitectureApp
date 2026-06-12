package com.androidengineer.feature.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidengineer.core.data.repository.PostsRepository
import kotlinx.coroutines.flow.StateFlow
import com.androidengineer.core.asResult
import com.androidengineer.core.Result
import com.androidengineer.core.ApiException
import com.androidengineer.feature.model.PostUiModel
import com.androidengineer.feature.toFeatureModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class PostsViewModel(
    postsRepository: PostsRepository
) : ViewModel() {

    val uiState: StateFlow<PostsUiState> = postsRepository.getPosts()
        .asResult()
        .map { result ->
            when (result) {
                is Result.Success -> PostsUiState.Success(result.data.map { post -> post.toFeatureModel() })
                is Result.Error -> {
                    val message = when (val exception = result.exception) {
                        is ApiException -> when (exception.code) {
                            401 -> "Session expired. Please login again."
                            404 -> "Posts not found."
                            500 -> "Server error. Please try again later."
                            else -> "Something went wrong (Error: ${exception.code})"
                        }
                        else -> exception.message ?: "Unknown error occurred"
                    }
                    PostsUiState.Error(message)
                }
                is Result.Loading -> PostsUiState.Loading
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PostsUiState.Loading
        )
}

sealed interface PostsUiState {
    data object Loading : PostsUiState
    data class Success(val postUiModels: List<PostUiModel>) : PostsUiState
    data class Error(val message: String) : PostsUiState
}
