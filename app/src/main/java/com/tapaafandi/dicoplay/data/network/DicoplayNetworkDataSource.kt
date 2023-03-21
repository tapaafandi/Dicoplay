package com.tapaafandi.dicoplay.data.network

import com.tapaafandi.dicoplay.data.network.dto.NetworkGame
import com.tapaafandi.dicoplay.data.network.dto.NetworkGameDetails
import retrofit2.Response

interface DicoplayNetworkDataSource {

    suspend fun getTopFreeToPlayGames(): Response<List<NetworkGame>>

    suspend fun getGameDetails(id: Int): Response<NetworkGameDetails>
}
