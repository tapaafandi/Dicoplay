package com.tapaafandi.dicoplay.domain.repository

import com.tapaafandi.dicoplay.domain.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

    val userData: Flow<UserData>

    suspend fun updateGamesBookmark(gameId: Int, bookmarked: Boolean)

}