package com.androidengineer.feature

import com.androidengineer.core.data.remote.model.PostResource
import com.androidengineer.feature.model.Post

fun PostResource.remoteToPresentationModel(): Post = Post(
    userId = userId,
    id = id,
    title = title,
    body = body
)