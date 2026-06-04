package com.androidengineer.androidarchitectureapp.data.repository

import com.androidengineer.androidarchitectureapp.data.asExternalModel
import com.androidengineer.androidarchitectureapp.data.remote.PostsRemoteDataSource
import com.androidengineer.androidarchitectureapp.presentation.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PostsRepositoryImpl(
    private val remoteDataSource: PostsRemoteDataSource
) : PostsRepository {
    override suspend operator fun invoke(): Flow<Result<List<Post>>> =
        remoteDataSource.fetchPosts()
            .asResult()
            .map { result ->
                result.map { posts ->
                    posts.map { post -> post.asExternalModel() }
                }
            }
}