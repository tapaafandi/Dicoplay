package com.tapaafandi.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tapaafandi.core.ui.GameFeedUiState
import com.tapaafandi.core.ui.gameFeed

@Composable
fun HomeRoute(
    onGameClick: (Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homeFeedUiState by viewModel.homeFeedUiState.collectAsStateWithLifecycle()
    HomeScreen(
        homeFeedUiState = homeFeedUiState,
        onGameClick = onGameClick
    )
}

@Composable
fun HomeScreen(
    homeFeedUiState: GameFeedUiState, onGameClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    val isHomeLoading = homeFeedUiState is GameFeedUiState.Loading
    val lazyGridState = rememberLazyGridState()

    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize(),
        columns = GridCells.Adaptive(300.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        state = lazyGridState
    ) {
        gameFeed(
            gameFeedState = homeFeedUiState,
            onGameClick = onGameClick
        )
    }

    if (isHomeLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}
