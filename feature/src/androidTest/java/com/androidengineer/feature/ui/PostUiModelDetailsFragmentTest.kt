package com.androidengineer.feature.ui

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.androidengineer.feature.model.PostUiModel
import com.androidengineer.feature.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PostUiModelDetailsFragmentTest {

    @Test
    fun postDetails_isDisplayed() {
        val post = PostUiModel(1, 1, "Test Title", "Test Body Detail")
        val fragmentArgs = Bundle().apply {
            putParcelable("post", post)
        }

        launchFragmentInContainer<PostDetailsFragment>(
            fragmentArgs = fragmentArgs,
            themeResId = com.google.android.material.R.style.Theme_MaterialComponents_DayNight
        )

        onView(withId(R.id.postDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.postDetail)).check(matches(withText("Test Body Detail")))
    }
}
