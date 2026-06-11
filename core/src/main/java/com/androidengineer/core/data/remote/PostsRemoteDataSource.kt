package com.androidengineer.core.data.remote

import com.androidengineer.core.data.remote.api.PostsApiService
import com.androidengineer.core.data.remote.model.PostResource

class PostsRemoteDataSource(
    private val apiService: PostsApiService
) {
    suspend fun fetchPosts(): List<PostResource> = apiService.getPosts()
}
