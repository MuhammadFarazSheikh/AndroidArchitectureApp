package com.androidengineer.feature.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.androidengineer.core.data.repository.PostsRepository
import com.androidengineer.core.domain.model.Post
import com.androidengineer.feature.R
import com.androidengineer.feature.viewmodels.PostsViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
class PostsListFragmentTest : KoinTest {

    private val postsRepository: PostsRepository = mockk()
    private val navController: NavController = mockk(relaxed = true)

    @Before
    fun setup() {
        stopKoin()
        startKoin {
            androidContext(ApplicationProvider.getApplicationContext())
            modules(module {
                single<PostsRepository> { postsRepository }
                viewModelOf(::PostsViewModel)
            })
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun postsList_isDisplayed() {
        val posts = listOf(
            Post(1, 1, "Test Title 1", "Test Body 1"),
            Post(1, 2, "Test Title 2", "Test Body 2")
        )
        every { postsRepository.getPosts() } returns flowOf(posts)

        val scenario = launchFragmentInContainer<PostsListFragment>(
            themeResId = com.google.android.material.R.style.Theme_MaterialComponents_DayNight
        )
        
        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        onView(withId(R.id.postsRecyclerView)).check(matches(isDisplayed()))
        onView(withText("Test Title 1")).check(matches(isDisplayed()))
        onView(withText("Test Title 2")).check(matches(isDisplayed()))
    }
}
