package com.androidengineer.core.data.repository

import app.cash.turbine.test
import com.androidengineer.core.data.local.PostsLocalDataSource
import com.androidengineer.core.data.local.roomdb.model.PostEntity
import com.androidengineer.core.data.remote.PostsRemoteDataSource
import com.androidengineer.core.data.remote.model.PostResource
import com.androidengineer.core.domain.model.Post
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PostsRepositoryImplTest {

    private lateinit var repository: PostsRepositoryImpl
    private val remoteDataSource: PostsRemoteDataSource = mockk()
    private val localDataSource: PostsLocalDataSource = mockk()

    @Before
    fun setup() {
        repository = PostsRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun `getPosts emits local posts then remote posts and saves them`() = runTest {
        val localEntities = listOf(PostEntity(1, 1, "Local", "Body"))
        val remoteResources = listOf(PostResource(1, 1, "Remote", "Body"))
        
        coEvery { localDataSource.getAllPosts() } returns localEntities
        coEvery { remoteDataSource.fetchPosts() } returns remoteResources
        coEvery { localDataSource.insertPost(any()) } returns Unit

        repository.getPosts().test {
            // First emission from local
            val firstEmission = awaitItem()
            assertEquals(1, firstEmission.size)
            assertEquals("Local", firstEmission[0].title)

            // Second emission after remote fetch
            val secondEmission = awaitItem()
            assertEquals(1, secondEmission.size)
            assertEquals("Remote", secondEmission[0].title)
            
            awaitComplete()
        }

        coVerify { localDataSource.insertPost(any()) }
    }
}
