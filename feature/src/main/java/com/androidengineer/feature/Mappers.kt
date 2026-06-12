package com.androidengineer.feature

import com.androidengineer.core.domain.model.Post
import com.androidengineer.feature.model.PostUiModel as PostUiModel

fun Post.toFeatureModel(): PostUiModel = PostUiModel(
    userId = userId,
    id = id,
    title = title,
    body = body
)