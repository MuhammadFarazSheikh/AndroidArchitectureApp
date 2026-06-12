package com.androidengineer.core

import com.androidengineer.core.data.local.roomdb.model.PostEntity
import com.androidengineer.core.data.remote.model.PostResource
import org.junit.Assert.assertEquals
import org.junit.Test

class MappersTest {

    @Test
    fun `PostResource toEntity correctly maps fields`() {
        val resource = PostResource(userId = 1, id = 10, title = "Title", body = "Body")
        val entity = resource.toEntity()

        assertEquals(resource.userId, entity.userId)
        assertEquals(resource.id, entity.id)
        assertEquals(resource.title, entity.title)
        assertEquals(resource.body, entity.body)
    }

    @Test
    fun `PostEntity localToDomainModel correctly maps fields`() {
        val entity = PostEntity(userId = 1, id = 10, title = "Title", body = "Body")
        val post = entity.localToDomainModel()

        assertEquals(entity.userId, post.userId)
        assertEquals(entity.id, post.id)
        assertEquals(entity.title, post.title)
        assertEquals(entity.body, post.body)
    }

    @Test
    fun `PostResource remoteToDomainModel correctly maps fields`() {
        val resource = PostResource(userId = 1, id = 10, title = "Title", body = "Body")
        val post = resource.remoteToDomainModel()

        assertEquals(resource.userId, post.userId)
        assertEquals(resource.id, post.id)
        assertEquals(resource.title, post.title)
        assertEquals(resource.body, post.body)
    }
}
