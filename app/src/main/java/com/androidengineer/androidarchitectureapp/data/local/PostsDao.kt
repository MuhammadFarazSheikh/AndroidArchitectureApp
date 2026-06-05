package com.androidengineer.androidarchitectureapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.androidengineer.androidarchitectureapp.data.local.model.PostEntity

@Dao
interface PostsDao {

    @Query("SELECT * FROM PostEntity")
    suspend fun getAllPosts(): List<PostEntity>

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insertPost(postEntityList: List<PostEntity>)
}