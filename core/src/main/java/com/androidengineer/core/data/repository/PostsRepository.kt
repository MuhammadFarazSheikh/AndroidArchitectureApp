package com.androidengineer.core.data.repository

import com.androidengineer.core.data.remote.model.PostResource
import kotlinx.coroutines.flow.Flow


interface PostsRepository {
    suspend operator fun invoke(): Flow<List<PostResource>>
}