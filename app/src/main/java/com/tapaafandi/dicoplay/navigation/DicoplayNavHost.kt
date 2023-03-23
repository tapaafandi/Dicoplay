package com.tapaafandi.dicoplay.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.tapaafandi.feature.game.navigation.gameDetailsScreen
import com.tapaafandi.feature.game.navigation.navigateToGameDetails
import com.tapaafandi.home.navigation.homeScreen

@Composable
fun DicoplayNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = com.tapaafandi.home.navigation.homeRoute
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
        gameDetailsScreen(
            onBackClick = navController::popBackStack
        )
    }
}