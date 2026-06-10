package com.androidengineer.feature.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.androidengineer.feature.model.Post
import com.androidengineer.feature.viewmodels.PostsUiState
import com.androidengineer.feature.viewmodels.PostsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PostsList(
    onClick: (Post) -> Unit = {}
) {
    val postsViewModel: PostsViewModel = koinViewModel()
    when (val state = postsViewModel.uiState.collectAsStateWithLifecycle().value) {
        is PostsUiState.Success -> PostListScreen(state.posts, onClick)
        is PostsUiState.Error -> {}
        is PostsUiState.Loading -> {}
    }
}

@Composable
fun PostListScreen(
    posts: List<Post>,
    onClick: (Post) -> Unit = {}
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(posts) { index, post ->
            PostListRow(post, onClick)
        }
    }

}

@Composable
fun PostListRow(
    post: Post,
    onClick: (Post) -> Unit = {}
) {

    Text(
        modifier = Modifier
            .clickable(onClick = {
                onClick.invoke(post)
            })
            .padding(7.dp, 7.dp, 7.dp, 0.dp),
        text = post.title,
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
    )

    HorizontalDivider(
        modifier = Modifier
            .padding(7.dp, 7.dp, 7.dp, 0.dp)
            .fillMaxWidth()
            .height(2.dp),
        thickness = 2.dp,
        color = Color.Gray
    )

}