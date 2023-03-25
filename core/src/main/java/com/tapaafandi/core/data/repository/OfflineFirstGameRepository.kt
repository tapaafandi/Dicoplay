package com.tapaafandi.core.data.repository

import com.tapaafandi.core.data.local.DicoplayDatabase
import com.tapaafandi.core.data.local.entities.GameEntity
import com.tapaafandi.core.data.local.entities.asExternalModel
import com.tapaafandi.core.data.network.DicoplayNetworkDataSource
import com.tapaafandi.core.data.network.dto.asEntity
import com.tapaafandi.core.domain.model.Game
import com.tapaafandi.core.domain.repository.GameRepository
import com.tapaafandi.core.domain.util.Resource
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class OfflineFirstGameRepository @Inject constructor(
    private val network: DicoplayNetworkDataSource,
    dicoplayDatabase: DicoplayDatabase
) : GameRepository {

    private val gameDao = dicoplayDatabase.gameDao()

    override fun getTopFreeToPlayGames(): Flow<List<Game>> {
        return gameDao.getGames().map { it.map(GameEntity::asExternalModel) }
    }

    override fun getGame(gameId: Int): Flow<Game> {
        return gameDao.getGameDetails(gameId = gameId).map { it.asExternalModel() }
    }

    override suspend fun updateGameInformationFromNetwork(gameId: Int) {
        val localGame = gameDao.getGameDetails(gameId).first()
        network.getGameDetails(gameId).collectLatest { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                        gameDao.updateGame(it.asEntity(localGame.id))
                    }
                }
                else -> Unit
            }
        }
    }

    override suspend fun synchronize(): Flow<Boolean> {
        return network.getTopFreeToPlayGames().map { result ->
            when (result) {
                is Resource.Error -> {
                    false
                }
                is Resource.Success -> {
                    result.data?.let {
                        gameDao.deleteGames()
                        gameDao.insertGames(result.data.map { it.asEntity() })
                    }
                    true
                }
            }
        }
    }
}