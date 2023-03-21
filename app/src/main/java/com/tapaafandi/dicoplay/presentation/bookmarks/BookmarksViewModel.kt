package com.tapaafandi.dicoplay.presentation.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapaafandi.dicoplay.domain.model.UserGameResource
import com.tapaafandi.dicoplay.domain.repository.UserDataRepository
import com.tapaafandi.dicoplay.domain.use_case.GetGameResourceUseCase
import com.tapaafandi.dicoplay.ui.GameFeedUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
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