package com.tapaafandi.core.domain.repository

import com.tapaafandi.core.domain.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

    val userData: Flow<UserData>

    suspend fun updateGamesBookmark(gameId: Int, bookmarked: Boolean)

}