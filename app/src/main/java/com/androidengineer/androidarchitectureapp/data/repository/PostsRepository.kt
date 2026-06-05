package com.androidengineer.androidarchitectureapp.data.repository

import com.androidengineer.androidarchitectureapp.presentation.model.Post
import kotlinx.coroutines.flow.Flow


interface PostsRepository {
    suspend operator fun invoke(): Flow<List<Post>>
}