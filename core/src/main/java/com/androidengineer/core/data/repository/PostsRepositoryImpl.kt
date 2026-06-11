package com.androidengineer.core.data.repository

import com.androidengineer.core.domain.model.Post
import com.androidengineer.core.data.local.PostsLocalDataSource
import com.androidengineer.core.localToDomainModel
import com.androidengineer.core.data.remote.PostsRemoteDataSource
import com.androidengineer.core.remoteToDomainModel
import com.androidengineer.core.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostsRepositoryImpl(
    private val remoteDataSource: PostsRemoteDataSource,
    private val localDataSource: PostsLocalDataSource
) : PostsRepository {
    override fun getPosts(): Flow<List<Post>> = flow {
        emit(localDataSource.getAllPosts().map { postEntity -> postEntity.localToDomainModel() })

        val posts = remoteDataSource.fetchPosts()
        localDataSource.insertPost(posts.map { postResource -> postResource.toEntity() })
        emit(posts.map { postResource -> postResource.remoteToDomainModel() })
    }
}