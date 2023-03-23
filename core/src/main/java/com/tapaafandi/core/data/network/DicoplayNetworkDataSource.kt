package com.tapaafandi.core.data.network

import com.tapaafandi.core.data.network.dto.NetworkGame
import com.tapaafandi.core.data.network.dto.NetworkGameDetails
import retrofit2.Response

interface DicoplayNetworkDataSource {

    suspend fun getTopFreeToPlayGames(): Response<List<NetworkGame>>

    suspend fun getGameDetails(id: Int): Response<NetworkGameDetails>
}
