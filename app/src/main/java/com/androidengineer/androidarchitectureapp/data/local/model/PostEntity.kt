package com.androidengineer.androidarchitectureapp.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostEntity(
    @PrimaryKey(autoGenerate = false) val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)
