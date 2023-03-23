package com.tapaafandi.core.data.repository

import com.tapaafandi.core.datastore.DicoplayPreferencesDataSource
import com.tapaafandi.core.domain.model.UserData
import com.tapaafandi.core.domain.repository.UserDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineFirstUserDataRepository @Inject constructor(
    private val dicoplayPreferencesDataSource: DicoplayPreferencesDataSource
): UserDataRepository {
    override val userData: Flow<UserData> =
        dicoplayPreferencesDataSource.userData

    override suspend fun updateGamesBookmark(gameId: Int, bookmarked: Boolean) {
        dicoplayPreferencesDataSource.toggleGameBookmark(gameId, bookmarked)
    }
}