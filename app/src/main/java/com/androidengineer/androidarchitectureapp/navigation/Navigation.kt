package com.androidengineer.androidarchitectureapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.androidengineer.feature.POST_DETAIL
import com.androidengineer.feature.POST_LIST
import com.androidengineer.feature.moveToPostDetails
import com.androidengineer.feature.ui.PostsList
import com.androidengineer.feature.viewmodels.PostDetailsScreen

@Composable
fun Navigation(
    paddingValues: PaddingValues,
    navController: NavHostController = rememberNavController()
) {

    NavHost(
        modifier = Modifier.padding(paddingValues).fillMaxSize(),
        navController = navController,
        startDestination = POST_LIST
    ) {
        composable(POST_LIST) {
            PostsList(
                onClick = { post ->
                    navController.moveToPostDetails(post)
                }
            )
        }

        composable("$POST_DETAIL/{postBody}") { backStackEntry ->
            val postBody = backStackEntry.arguments?.getString("postBody")
            postBody?.let {
                PostDetailsScreen(postBody)
            }
        }
    }

}