package com.repleyva.gamexapp.ui.bookmark

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun BookmarkScreen(
    navigator: DestinationsNavigator,
) {
    Text(text = "Bookmark")
}