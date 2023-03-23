package com.tapaafandi.core.domain.repository

import com.tapaafandi.core.data.local.entities.GameEntity
import com.tapaafandi.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameRepository {

    fun getTopFreeToPlayGames(): Flow<List<Game>>

    fun getGame(gameId: Int): Flow<Game>

    suspend fun updateGameInformationFromNetwork(gameId: Int)

    suspend fun insertGames(games: List<GameEntity>)

    suspend fun synchronize(): Boolean
}