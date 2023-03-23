package com.tapaafandi.feature.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapaafandi.core.domain.model.UserGameResource
import com.tapaafandi.core.domain.repository.UserDataRepository
import com.tapaafandi.core.domain.use_case.GetGameResourceUseCase
import com.tapaafandi.core.ui.GameFeedUiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class BookmarksViewModel(
    private val userDataRepository: UserDataRepository,
    getGameResourceUseCase: GetGameResourceUseCase
): ViewModel() {

    val saveFeedUiState: StateFlow<GameFeedUiState> = getGameResourceUseCase()
        .filterNot { it.isEmpty() }
        .map { gameResources -> gameResources.filter(UserGameResource::isSaved) }
        .map<List<UserGameResource>, GameFeedUiState>(GameFeedUiState::Success)
        .onStart { emit(GameFeedUiState.Loading) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = GameFeedUiState.Loading
        )

    fun removeFromBookmarks(gameResourceId: Int) {
        viewModelScope.launch {
            userDataRepository.updateGamesBookmark(gameResourceId, false)
        }
    }
}