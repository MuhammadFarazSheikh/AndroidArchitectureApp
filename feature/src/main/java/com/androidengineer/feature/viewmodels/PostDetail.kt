package com.androidengineer.feature.viewmodels

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PostDetailsScreen(
    postBody: String
) {

    Text(
        modifier = Modifier.fillMaxSize().padding(10.dp),
        text = postBody,
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold
    )

}