package com.tapaafandi.dicoplay.data.network.dto

import com.squareup.moshi.Json
import com.tapaafandi.dicoplay.data.local.entities.GameEntity

data class NetworkGameDetails(
    @field:Json(name = "description")
    val description: String,
    @field:Json(name = "developer")
    val developer: String,
    @field:Json(name = "freetogame_profile_url")
    val freetogameProfileUrl: String,
    @field:Json(name = "game_url")
    val gameUrl: String,
    @field:Json(name = "genre")
    val genre: String,
    @field:Json(name = "id")
    val gameId: Int,
    @field:Json(name = "minimum_system_requirements")
    val minimumSystemRequirements: NetworkMinSpec,
    @field:Json(name = "platform")
    val platform: String,
    @field:Json(name = "publisher")
    val publisher: String,
    @field:Json(name = "release_date")
    val releaseDate: String,
    @field:Json(name = "screenshots")
    val screenshots: List<NetworkScreenshot>,
    @field:Json(name = "short_description")
    val shortDescription: String,
    @field:Json(name = "status")
    val status: String,
    @field:Json(name = "thumbnail")
    val thumbnail: String,
    @field:Json(name = "title")
    val title: String
)

fun NetworkGameDetails.asEntity(id: Int): GameEntity {
    return GameEntity(
        id = id,
        gameId = gameId,
        description = description,
        developer = developer,
        freeToGameProfileUrl = freetogameProfileUrl,
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