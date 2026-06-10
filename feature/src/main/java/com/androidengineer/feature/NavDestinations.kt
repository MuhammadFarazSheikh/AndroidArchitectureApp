package com.androidengineer.feature

import android.net.Uri
import androidx.navigation.NavHostController
import com.androidengineer.feature.model.Post

const val POST_LIST = "post_list"
const val POST_DETAIL = "post_detail"

fun NavHostController.moveToPostDetails(
    post: Post
) = navigate(
    POST_DETAIL + "/${Uri.encode(post.body)}"
)