package com.tapaafandi.feature.game

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapaafandi.core.domain.model.BookmarkableGame
import com.tapaafandi.core.domain.model.Game
import com.tapaafandi.core.domain.repository.GameRepository
import com.tapaafandi.core.domain.repository.UserDataRepository
import com.tapaafandi.core.domain.util.asResult
import com.tapaafandi.feature.game.navigation.gameIdArg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import com.tapaafandi.core.domain.util.Result
import javax.inject.Inject

@HiltViewModel
class GameDetailsViewModel @Inject constructor(
    gameRepository: GameRepository,
    private val userDataRepository: UserDataRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val gameId = checkNotNull(savedStateHandle.get<Int>(gameIdArg))

    val gameUiState: StateFlow<GameDetailsUiState> = gameUiState(
        gameId = gameId,
        gameRepository = gameRepository,
        userDataRepository = userDataRepository
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = GameDetailsUiState.Loading
    )

    fun bookmarkGame(gameResourceId: Int, bookmarked: Boolean) {
        viewModelScope.launch {
            userDataRepository.updateGamesBookmark(gameResourceId, bookmarked)
        }
    }
}

private fun gameUiState(
    gameId: Int,
    userDataRepository: UserDataRepository,
    gameRepository: GameRepository,
): Flow<GameDetailsUiState> {

    val bookmarkedGameIds: Flow<Set<Int>> = userDataRepository.userData.map { it.bookmarkedGames }
    val gameStream: Flow<Game> = gameRepository.getGame(gameId = gameId)

    return combine(
        bookmarkedGameIds,
        gameStream,
        ::Pair
    )
        .asResult()
        .map { bookmarkedGameToGameResult ->
            when (bookmarkedGameToGameResult) {
                is Result.Error -> GameDetailsUiState.Error
                is Result.Loading -> GameDetailsUiState.Loading
                is Result.Success -> {
                    val (bookmarkedGame, game) = bookmarkedGameToGameResult.data
                    val isBookmarked = bookmarkedGame.contains(gameId)

                    if (game.description.isNullOrBlank()) {
                        gameRepository.updateGameInformationFromNetwork(gameId)
                    }

                    GameDetailsUiState.Success(
                        bookmarkableGame = BookmarkableGame(game, isBookmarked)
                    )
                }
            }
        }
}

sealed interface GameDetailsUiState {
    data class Success(val bookmarkableGame: BookmarkableGame) : GameDetailsUiState
    object Error : GameDetailsUiState
    object Loading : GameDetailsUiState
}