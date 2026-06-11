package com.androidengineer.core.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.androidengineer.core.data.local.roomdb.model.PostEntity
import com.androidengineer.core.data.local.roomdb.PostsDao
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.first

private val KEY_POSTS = stringPreferencesKey("posts")

class PostsLocalDataSource(
    private val postsDao: PostsDao,
    private val dataStore: DataStore<Preferences>
) {
    suspend fun insertPost(postEntity: List<PostEntity>) = insertInRoomDB(postEntity)

    suspend fun getAllPosts() = getAllPostsFromRoomDB()

    suspend fun insertInRoomDB(postEntity: List<PostEntity>) = postsDao.insertPost(postEntity)

    suspend fun getAllPostsFromRoomDB() = postsDao.getAllPosts()

    suspend fun insertInDataStore(postEntity: List<PostEntity>) = dataStore.edit {
        it[KEY_POSTS] = Gson().toJson(postEntity)
    }

    suspend fun getFromDataStore() = Gson().fromJson<List<PostEntity>>(
        dataStore.data.first()[KEY_POSTS] ?: "[]",
        object : TypeToken<List<PostEntity>>() {}.type
    ) ?: emptyList()

}