package com.tapaafandi.dicoplay

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.tapaafandi.dicoplay.data.util.NetworkMonitor
import com.tapaafandi.dicoplay.navigation.TopLevelDestination
import com.tapaafandi.dicoplay.presentation.bookmarks.navigation.bookmarksRoute
import com.tapaafandi.dicoplay.presentation.bookmarks.navigation.navigateToBookmarks
import com.tapaafandi.dicoplay.presentation.home.navigation.homeRoute
import com.tapaafandi.dicoplay.presentation.home.navigation.navigateToHome
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Composable
fun rememberDicoplayAppState(
    windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()
): DicoplayAppState {
    return remember(navController, coroutineScope, windowSizeClass, networkMonitor) {
        DicoplayAppState(navController, coroutineScope, windowSizeClass, networkMonitor)
    }
}

@Stable
class DicoplayAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            homeRoute -> TopLevelDestination.HOME
            bookmarksRoute -> TopLevelDestination.BOOKMARKS
            else -> null
        }

    val shouldShowBottomBar: Boolean
        get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

    val shouldShowNavRail: Boolean
        get() = !shouldShowBottomBar

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_0000),
            initialValue = false
        )

    val topLevelDestination: List<TopLevelDestination> = TopLevelDestination.values().asList()

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.HOME -> navController.navigateToHome(topLevelNavOptions)
            TopLevelDestination.BOOKMARKS -> navController.navigateToBookmarks(topLevelNavOptions)
        }
    }
}