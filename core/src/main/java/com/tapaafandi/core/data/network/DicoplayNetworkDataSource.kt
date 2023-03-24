package com.tapaafandi.core.data.network

import com.tapaafandi.core.data.network.dto.NetworkGame
import com.tapaafandi.core.data.network.dto.NetworkGameDetails
import com.tapaafandi.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface DicoplayNetworkDataSource {

    suspend fun getTopFreeToPlayGames(): Flow<Resource<List<NetworkGame>>>

    suspend fun getGameDetails(id: Int): Flow<Resource<NetworkGameDetails>>
}
