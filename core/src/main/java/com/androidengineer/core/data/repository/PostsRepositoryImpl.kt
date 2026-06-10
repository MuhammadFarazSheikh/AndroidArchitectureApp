package com.androidengineer.core.data.repository

import com.androidengineer.core.data.local.PostsLocalDataSource
import com.androidengineer.core.data.localToRemoteModel
import com.androidengineer.core.data.remote.PostsRemoteDataSource
import com.androidengineer.core.data.remote.model.PostResource
import com.androidengineer.core.data.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlin.collections.map

class PostsRepositoryImpl(
    private val remoteDataSource: PostsRemoteDataSource,
    private val localDataSource: PostsLocalDataSource
) : PostsRepository {
    override suspend operator fun invoke(): Flow<List<PostResource>> =
        remoteDataSource.fetchPosts()
            .map { posts -> posts.map { post -> post.toEntity() } }
            .onEach { posts ->
                localDataSource.insertPost(posts)
            }
            .map { posts -> posts.map { post -> post.localToRemoteModel() } }
            .catch {
                emit(localDataSource.getAllPosts().map { post -> post.localToRemoteModel() })
            }
}