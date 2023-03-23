package com.tapaafandi.core.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import com.tapaafandi.core.domain.model.UserData
import com.tapaafandi.dicoplay.proto.UserPreferences
import com.tapaafandi.dicoplay.proto.copy
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class DicoplayPreferencesDataSource @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>
) {
    val userData = userPreferences.data
        .map {
            UserData(
                bookmarkedGames = it.bookmarkedGameResourceIdsMap.keys
            )
        }

    suspend fun toggleGameBookmark(gameId: Int, isBookmarked: Boolean) {
        try {
            userPreferences.updateData {
                it.copy {
                    if (isBookmarked) {
                        bookmarkedGameResourceIds.put(gameId, true)
                    } else {
                        bookmarkedGameResourceIds.remove(gameId)
                    }
                }
            }
        } catch (e: IOException) {
            Log.e("DicoplayPreferences", "Failed to update user preferences.")
        }
    }
}
