package com.tapaafandi.core.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tapaafandi.core.domain.model.Game

@Entity(tableName = "games")
data class GameEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val gameId: Int,
    val description: String? = null,
    val developer: String,
    @ColumnInfo("freetogame_profile_url")
    val freeToGameProfileUrl: String,
    @ColumnInfo("game_url")
    val gameUrl: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    @ColumnInfo("release_date")
    val releaseDate: String,
    @ColumnInfo("short_date")
    val shortDescription: String,
    val thumbnail: String,
    val title: String
)

fun GameEntity.asExternalModel(): Game {
    return Game(
        id = id,
        gameId = gameId,
        description = description,
        developer = developer,
        freeToGameProfileUrl = freeToGameProfileUrl,
        gameUrl = gameUrl,
        genre = genre,
        platform = platform,
        publisher = publisher,
        releaseDate = releaseDate,
        shortDescription = shortDescription,
        thumbnail = thumbnail,
        title = title
    )
}
