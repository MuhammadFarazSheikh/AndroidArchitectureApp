package com.androidengineer.core.data.local.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.androidengineer.core.data.local.roomdb.model.PostEntity

@Database(entities = [PostEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postsDao(): PostsDao
}