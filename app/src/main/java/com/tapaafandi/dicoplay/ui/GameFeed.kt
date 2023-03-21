package com.tapaafandi.dicoplay.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.tapaafandi.dicoplay.R
import com.tapaafandi.dicoplay.domain.model.UserGameResource

fun LazyGridScope.gameFeed(
    gameFeedState: GameFeedUiState,
    onGameClick: (Int) -> Unit
) {
    when (gameFeedState) {
        is GameFeedUiState.Success -> {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Text(
                    text = stringResource(R.string.games_for_you),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            itemsIndexed(
                gameFeedState.gameFeed,
            ) { index, userGameResource ->
                val number by remember {
                    mutableStateOf(index.plus(1).toString())
                }
                GameResourceSimple(
                    userGameResource = userGameResource,
                    onGameClick = { onGameClick(it) },
                    gameNumber = number
                )
            }
        }
        else -> Unit
    }
}

sealed interface GameFeedUiState {
    data class Success(val gameFeed: List<UserGameResource>) : GameFeedUiState
    object Loading : GameFeedUiState
}