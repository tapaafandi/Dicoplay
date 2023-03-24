package com.tapaafandi.core.data.network.retrofit

import com.tapaafandi.core.data.network.DicoplayNetworkDataSource
import com.tapaafandi.core.data.network.dto.NetworkGame
import com.tapaafandi.core.data.network.dto.NetworkGameDetails
import com.tapaafandi.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class RetrofitDicoplayNetwork @Inject constructor(
    private val api: RetrofitDicoplayNetworkApi
) : DicoplayNetworkDataSource {

    override suspend fun getTopFreeToPlayGames(): Flow<Resource<List<NetworkGame>>> = flow {
        try {
            val response = api.getTopFreeToPlayGames()
            if (response.isSuccessful) {
                response.body()?.let { networkGames ->
                    emit(Resource.Success(networkGames))
                }
            } else {
                if (response.code().toString().startsWith("5")) {
                    emit(Resource.Error("Network error"))
                }
            }
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
            e.printStackTrace()
        }
    }

    override suspend fun getGameDetails(id: Int): Flow<Resource<NetworkGameDetails>> = flow {
        try {
            val response = api.getGameDetails(id)
            if (response.isSuccessful) {
                response.body()?.let { networkGame ->
                    emit(Resource.Success(networkGame))
                }
            }
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
            e.printStackTrace()
        }
    }
}