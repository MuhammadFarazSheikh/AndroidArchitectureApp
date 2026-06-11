package com.androidengineer.core.data.remote.api

import com.androidengineer.core.data.remote.model.PostResource
import retrofit2.http.GET

interface PostsApiService {
    @GET("posts")
    suspend fun getPosts(): List<PostResource>
}