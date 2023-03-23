package com.tapaafandi.core.data.network.dto

import com.squareup.moshi.Json
import com.tapaafandi.core.data.local.entities.GameEntity

data class NetworkGame(
    @field:Json(name = "developer")
    val developer: String,
    @field:Json(name = "freetogame_profile_url")
    val freeToGameProfileUrl: String,
    @field:Json(name = "game_url")
    val gameUrl: String,
    @field:Json(name = "genre")
    val genre: String,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "platform")
    val platform: String,
    @field:Json(name = "publisher")
    val publisher: String,
    @field:Json(name = "release_date")
    val releaseDate: String,
    @field:Json(name = "short_description")
    val shortDescription: String,
    @field:Json(name = "thumbnail")
    val thumbnail: String,
    @field:Json(name = "title")
    val title: String
)

fun NetworkGame.asEntity(): GameEntity {
    return GameEntity(
        id = 0,
        developer = developer,
        freeToGameProfileUrl = freeToGameProfileUrl,
        gameUrl = gameUrl,
        genre = genre,
        gameId = id,
        platform = platform,
        publisher = publisher,
        releaseDate = releaseDate,
        shortDescription = shortDescription,
        thumbnail = thumbnail,
        title = title
    )
}