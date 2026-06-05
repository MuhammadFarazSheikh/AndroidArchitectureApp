package com.androidengineer.androidarchitectureapp.data.local

import com.androidengineer.androidarchitectureapp.data.local.model.PostEntity

class PostsLocalDataSource (
    private val postsDao: PostsDao
) {

    suspend fun insertPost(postEntity: List<PostEntity>) = postsDao.insertPost(postEntity)

    suspend fun getAllPosts() = postsDao.getAllPosts()

}