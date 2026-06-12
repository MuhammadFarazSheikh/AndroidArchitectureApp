package com.androidengineer.core

import app.cash.turbine.test
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class ResultTest {

    @Test
    fun `asResult emits Loading then Success`() = runTest {
        val data = "test data"
        val flow = flowOf(data)

        flow.asResult().test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(data), awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `asResult catches IOException and emits Error with custom message`() = runTest {
        val exception = IOException("No internet")
        val flow = flow<String> { throw exception }

        flow.asResult().test {
            assertEquals(Result.Loading, awaitItem())
            val error = awaitItem() as Result.Error
            assertTrue(error.exception is Exception)
            assertEquals("Network connection error", error.exception.message)
            awaitComplete()
        }
    }

    @Test
    fun `asResult catches HttpException and emits Error with ApiException`() = runTest {
        val response = Response.error<String>(404, "".toResponseBody())
        val exception = HttpException(response)
        val flow = flow<String> { throw exception }

        flow.asResult().test {
            assertEquals(Result.Loading, awaitItem())
            val error = awaitItem() as Result.Error
            val apiException = error.exception as ApiException
            assertEquals(404, apiException.code)
            awaitComplete()
        }
    }
}
