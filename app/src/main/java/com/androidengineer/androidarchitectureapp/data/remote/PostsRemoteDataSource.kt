package com.androidengineer.androidarchitectureapp.data.remote

import com.androidengineer.androidarchitectureapp.data.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostsRemoteDataSource(
    private val apiService: PostsApiService
) {
    suspend fun fetchPosts(): Flow<List<Post>> = flow {
        emit(apiService.getPosts())
    }
}
