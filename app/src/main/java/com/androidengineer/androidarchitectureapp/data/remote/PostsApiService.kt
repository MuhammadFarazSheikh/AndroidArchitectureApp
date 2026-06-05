package com.androidengineer.androidarchitectureapp.data.remote

import com.androidengineer.androidarchitectureapp.data.remote.model.PostResource
import retrofit2.http.GET

interface PostsApiService {
    @GET("posts")
    suspend fun getPosts(): List<PostResource>
}
