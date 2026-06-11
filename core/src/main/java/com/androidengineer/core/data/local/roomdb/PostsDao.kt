package com.androidengineer.core.data.local.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.androidengineer.core.data.local.roomdb.model.PostEntity

@Dao
interface PostsDao {

    @Query("SELECT * FROM PostEntity")
    suspend fun getAllPosts(): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(postEntityList: List<PostEntity>)
}