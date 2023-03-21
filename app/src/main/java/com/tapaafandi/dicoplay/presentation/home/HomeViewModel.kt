package com.tapaafandi.dicoplay.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapaafandi.dicoplay.domain.model.UserGameResource
import com.tapaafandi.dicoplay.domain.use_case.GetGameResourceUseCase
import com.tapaafandi.dicoplay.ui.GameFeedUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getGameResourceUseCase: GetGameResourceUseCase
) : ViewModel() {

    val homeFeedUiState: StateFlow<GameFeedUiState> = getGameResourceUseCase.invoke()
        .map<List<UserGameResource>, GameFeedUiState>(GameFeedUiState::Success)
        .onStart { emit(GameFeedUiState.Loading) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = GameFeedUiState.Loading
        )
}

