package com.tapaafandi.dicoplay.presentation.bookmarks.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.tapaafandi.dicoplay.presentation.bookmarks.BookmarksRoute

const val bookmarksRoute = "bookmarks_route"

fun NavController.navigateToBookmarks(navOptions: NavOptions? = null) {
    this.navigate(bookmarksRoute, navOptions)
}

fun NavGraphBuilder.bookmarksScreen(
    onGameCLick: (Int) -> Unit
) {
    composable(route = bookmarksRoute) {
        BookmarksRoute(
            onGameCLick = onGameCLick
        )
    }
}