package com.androidengineer.androidarchitectureapp.data.remote

import com.androidengineer.androidarchitectureapp.data.model.Post
import retrofit2.http.GET

interface PostsApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>
}
