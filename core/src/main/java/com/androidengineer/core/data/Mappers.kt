package com.androidengineer.core.data

import com.androidengineer.core.domain.model.Post
import com.androidengineer.core.data.remote.model.PostResource
import com.androidengineer.core.data.local.model.PostEntity

fun PostResource.toEntity(): PostEntity = PostEntity(
    userId = userId,
    id = id,
    title = title,
    body = body
)

fun PostEntity.localToDomainModel(): Post = Post(
    userId = userId,
    id = id,
    title = title,
    body = body
)

fun PostResource.remoteToDomainModel(): Post = Post(
    userId = userId,
    id = id,
    title = title,
    body = body
)