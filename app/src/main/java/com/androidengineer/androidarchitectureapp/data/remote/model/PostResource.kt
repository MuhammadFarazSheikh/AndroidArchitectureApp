package com.androidengineer.androidarchitectureapp.data.remote.model

data class PostResource(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)