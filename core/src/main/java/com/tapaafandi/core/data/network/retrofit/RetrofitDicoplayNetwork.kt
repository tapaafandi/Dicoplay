package com.tapaafandi.core.data.network.retrofit

import com.tapaafandi.core.data.network.DicoplayNetworkDataSource
import com.tapaafandi.core.data.network.dto.NetworkGame
import com.tapaafandi.core.data.network.dto.NetworkGameDetails
import retrofit2.Response
import javax.inject.Inject

class RetrofitDicoplayNetwork @Inject constructor(
    private val api: RetrofitDicoplayNetworkApi
) : DicoplayNetworkDataSource {
    override suspend fun getTopFreeToPlayGames(): Response<List<NetworkGame>> {
        return api.getTopFreeToPlayGames()
    }

    override suspend fun getGameDetails(id: Int): Response<NetworkGameDetails> {
        return api.getGameDetails(id)
    }

}