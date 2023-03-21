package com.tapaafandi.dicoplay.presentation.bookmarks

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tapaafandi.dicoplay.R
import com.tapaafandi.dicoplay.ui.GameFeedUiState
import com.tapaafandi.dicoplay.ui.GameResourceCard

@Composable
fun BookmarksRoute(
    modifier: Modifier = Modifier,
    onGameCLick: (Int) -> Unit,
    viewModel: BookmarksViewModel = hiltViewModel()
) {
    val savedFeedState by viewModel.saveFeedUiState.collectAsStateWithLifecycle()
    BookmarksScreen(
        savedFeedState = savedFeedState,
        onGameCLick = onGameCLick,
        removeFromBookmarks = viewModel::removeFromBookmarks,
        modifier = modifier
    )
}

@Composable
fun BookmarksScreen(
    savedFeedState: GameFeedUiState,
    onGameCLick: (Int) -> Unit,
    removeFromBookmarks: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    when (savedFeedState) {
        is GameFeedUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is GameFeedUiState.Success -> if (savedFeedState.gameFeed.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(300.dp),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                modifier = modifier.fillMaxSize()
            ) {
                savedGame(
                    savedFeedState = savedFeedState,
                    onGameResourceCheckedChanged = { id, _ ->
                        removeFromBookmarks(id)
                    },
                    onGameCLick = onGameCLick
                )
            }
        } else {
            EmptyBookmarkState()
        }
    }
}

fun LazyGridScope.savedGame(
    savedFeedState: GameFeedUiState,
    onGameResourceCheckedChanged: (Int, Boolean) -> Unit,
    onGameCLick: (Int) -> Unit
) {
    when (savedFeedState) {
        is GameFeedUiState.Success -> {
            items(
                savedFeedState.gameFeed, key = { it.id }
            ) { userGameResource ->
                GameResourceCard(
                    userGameResource = userGameResource,
                    isBookmarked = userGameResource.isSaved,
                    onToggleBookmark = {
                        onGameResourceCheckedChanged(
                            userGameResource.gameId,
                            !userGameResource.isSaved
                        )
                    },
                    onGameClick = onGameCLick
                )
            }
        }
        else -> Unit
    }
}

@Composable
fun EmptyBookmarkState(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.svg_undraw_add_notes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .size(200.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.no_saved_game),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = stringResource(R.string.no_saved_description))
    }
}