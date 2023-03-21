package com.tapaafandi.dicoplay.data.network.retrofit

import com.tapaafandi.dicoplay.data.network.dto.NetworkGame
import com.tapaafandi.dicoplay.data.network.dto.NetworkGameDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitDicoplayNetworkApi {

    @GET("games")
    suspend fun getTopFreeToPlayGames(
        @Query("sort-by") sortBy: String = "popularity"
    ): Response<List<NetworkGame>>

    @GET("game")
    suspend fun getGameDetails(
        @Query("id") id: Int
    ): Response<NetworkGameDetails>
}