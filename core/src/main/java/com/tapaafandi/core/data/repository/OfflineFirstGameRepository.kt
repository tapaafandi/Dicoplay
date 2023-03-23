package com.tapaafandi.core.data.repository

import com.tapaafandi.core.data.local.DicoplayDatabase
import com.tapaafandi.core.data.local.entities.GameEntity
import com.tapaafandi.core.data.local.entities.asExternalModel
import com.tapaafandi.core.data.network.DicoplayNetworkDataSource
import com.tapaafandi.core.data.network.dto.asEntity
import com.tapaafandi.core.domain.model.Game
import com.tapaafandi.core.domain.repository.GameRepository
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject

class OfflineFirstGameRepository @Inject constructor(
    private val network: DicoplayNetworkDataSource,
    private val dicoplayDatabase: DicoplayDatabase
) : GameRepository {

    private val gameDao = dicoplayDatabase.gameDao()

    override fun getTopFreeToPlayGames(): Flow<List<Game>> {
        return gameDao.getGames().map { it.map(GameEntity::asExternalModel) }
    }

    override fun getGame(gameId: Int): Flow<Game> {
        return gameDao.getGameDetails(gameId = gameId).map { it.asExternalModel() }
    }

    override suspend fun updateGameInformationFromNetwork(gameId: Int) {
        try {
            val localGame = gameDao.getGameDetails(gameId).first()
            val response = network.getGameDetails(gameId)
            response.body()?.let { networkGameDetails ->
                gameDao.upsertGame(networkGameDetails.asEntity(localGame.id))
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override suspend fun insertGames(games: List<GameEntity>) {
        gameDao.insertGames(games = games)
    }

    override suspend fun synchronize(): Boolean {
        val response = network.getTopFreeToPlayGames()
        if (response.isSuccessful) {
            response.body()?.let { networkGames ->
                gameDao.deleteGames()
                gameDao.insertGames(networkGames.map { it.asEntity() })
                return true
            }
        } else {
            return false
        }
        return false
    }
}