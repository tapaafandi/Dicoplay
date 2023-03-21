package com.tapaafandi.dicoplay.presentation.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.tapaafandi.dicoplay.presentation.home.HomeRoute

const val homeRoute = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(homeRoute, navOptions)
}

fun NavGraphBuilder.homeScreen(
    onGameClick: (Int) -> Unit
) {
    composable(route = homeRoute) {
        HomeRoute(
            onGameClick = onGameClick
        )
    }
}