package com.androidengineer.androidarchitectureapp.data

import com.androidengineer.androidarchitectureapp.data.remote.model.PostResource
import com.androidengineer.androidarchitectureapp.presentation.model.Post
import com.androidengineer.androidarchitectureapp.data.local.model.PostEntity

fun PostResource.remoteToPresentationModel(): Post = Post(
    userId = userId,
    id = id,
    title = title,
    body = body
)

fun PostResource.toEntity(): PostEntity = PostEntity(
    userId = userId,
    id = id,
    title = title,
    body = body
)

fun PostEntity.localToPresentationModel(): Post = Post(
    userId = userId,
    id = id,
    title = title,
    body = body
)