package com.tapaafandi.dicoplay.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.tapaafandi.dicoplay.presentation.bookmarks.navigation.bookmarksScreen
import com.tapaafandi.dicoplay.presentation.game_details.navigation.gameDetailsScreen
import com.tapaafandi.dicoplay.presentation.game_details.navigation.navigateToGameDetails
import com.tapaafandi.dicoplay.presentation.home.navigation.homeRoute
import com.tapaafandi.dicoplay.presentation.home.navigation.homeScreen

@Composable
fun DicoplayNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = homeRoute
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        homeScreen(
            onGameClick = {
                navController.navigateToGameDetails(it)
            }
        )
        bookmarksScreen(
            onGameCLick = {
                navController.navigateToGameDetails(it)
            }
        )
        gameDetailsScreen(
            onBackClick = navController::popBackStack
        )
    }
}