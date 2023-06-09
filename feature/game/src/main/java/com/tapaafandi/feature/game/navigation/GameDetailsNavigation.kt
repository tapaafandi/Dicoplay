package com.tapaafandi.feature.game.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.tapaafandi.feature.game.GameDetailsRoute

internal const val gameIdArg = "gameId"

fun NavController.navigateToGameDetails(gameId: Int, navOptions: NavOptions? = null) {
    this.navigate("home_route/$gameId", navOptions)
}

fun NavGraphBuilder.gameDetailsScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = "home_route/{$gameIdArg}",
        arguments = listOf(
            navArgument(gameIdArg) { type = NavType.IntType }
        )
    ) {
        GameDetailsRoute(
            onBackClick = onBackClick
        )
    }
}