package com.androidengineer.feature.viewmodels

import app.cash.turbine.test
import com.androidengineer.core.ApiException
import com.androidengineer.core.data.repository.PostsRepository
import com.androidengineer.feature.model.PostUiModel
import com.androidengineer.core.domain.model.Post
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PostsViewModelTest {

    private val postsRepository: PostsRepository = mockk()
    private lateinit var viewModel: PostsViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `uiState starts with Loading then Success`() = runTest {
        val posts = listOf(Post(1, 1, "Title", "Body"))
        val postUiModels = listOf(PostUiModel(1, 1, "Title", "Body"))
        every { postsRepository.getPosts() } returns flowOf(posts)

        viewModel = PostsViewModel(postsRepository)

        viewModel.uiState.test {
            assertEquals(PostsUiState.Loading, awaitItem())
            assertEquals(PostsUiState.Success(postUiModels), awaitItem())
        }
    }

    @Test
    fun `uiState emits Error message when 404 ApiException occurs`() = runTest {
        val exception = ApiException(code = 404, message = "Not Found")
        every { postsRepository.getPosts() } returns flow { throw exception }

        viewModel = PostsViewModel(postsRepository)

        viewModel.uiState.test {
            assertEquals(PostsUiState.Loading, awaitItem())
            val state = awaitItem() as PostsUiState.Error
            assertEquals("Posts not found.", state.message)
        }
    }

    @Test
    fun `uiState emits Generic error message for unknown exception`() = runTest {
        val exception = RuntimeException("Something went wrong")
        every { postsRepository.getPosts() } returns flow { throw exception }

        viewModel = PostsViewModel(postsRepository)

        viewModel.uiState.test {
            assertEquals(PostsUiState.Loading, awaitItem())
            val state = awaitItem() as PostsUiState.Error
            assertEquals("Something went wrong", state.message)
        }
    }
}
