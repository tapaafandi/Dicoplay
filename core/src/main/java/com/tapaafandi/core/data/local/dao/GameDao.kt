package com.tapaafandi.core.data.local.dao

import androidx.room.*
import com.tapaafandi.core.data.local.entities.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM games")
    fun getGames(): Flow<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGames(games: List<GameEntity>)

    @Upsert
    suspend fun upsertGame(game: GameEntity)

    @Query("SELECT * FROM games WHERE gameId = :gameId")
    fun getGameDetails(gameId: Int): Flow<GameEntity>

    @Query("DELETE FROM games")
    suspend fun deleteGames()
}