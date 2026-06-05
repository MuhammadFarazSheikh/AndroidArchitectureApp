package com.androidengineer.androidarchitectureapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.androidengineer.androidarchitectureapp.data.local.model.PostEntity

@Database(entities = [PostEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postsDao(): PostsDao
}