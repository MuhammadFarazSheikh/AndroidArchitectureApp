package com.androidengineer.core.data.repository

import com.androidengineer.core.domain.model.Post
import kotlinx.coroutines.flow.Flow


interface PostsRepository {
    fun getPosts(): Flow<List<Post>>
}