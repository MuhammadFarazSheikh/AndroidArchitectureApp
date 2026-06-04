package com.androidengineer.androidarchitectureapp.data

import com.androidengineer.androidarchitectureapp.data.model.Post
import com.androidengineer.androidarchitectureapp.presentation.model.Post as PostModel

fun Post.asExternalModel(): PostModel = PostModel(
    userId = userId,
    id = id,
    title = title,
    body = body
)
