package com.androidengineer.androidarchitectureapp.data.repository

import com.androidengineer.androidarchitectureapp.data.local.PostsLocalDataSource
import com.androidengineer.androidarchitectureapp.data.localToPresentationModel
import com.androidengineer.androidarchitectureapp.data.remote.PostsRemoteDataSource
import com.androidengineer.androidarchitectureapp.data.toEntity
import com.androidengineer.androidarchitectureapp.presentation.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class PostsRepositoryImpl(
    private val remoteDataSource: PostsRemoteDataSource,
    private val localDataSource: PostsLocalDataSource
) : PostsRepository {
    override suspend operator fun invoke(): Flow<List<Post>> =
        remoteDataSource.fetchPosts()
            .map { posts -> posts.map { post -> post.toEntity() } }
            .onEach { posts ->
                localDataSource.insertPost(posts)
            }
            .map { posts -> posts.map { post -> post.localToPresentationModel() } }
            .catch {
                emit(localDataSource.getAllPosts().map { post -> post.localToPresentationModel() })
            }
}