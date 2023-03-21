package com.tapaafandi.dicoplay.domain.model

data class GameDetails(
    val description: String,
    val developer: String,
    val freeToGameProfileUrl: String,
    val gameUrl: String,
    val genre: String,
    val id: Int,
    val minimumSystemRequirements: MinSpec,
    val platform: String,
    val publisher: String,
    val releaseDate: String,
    val screenshots: List<Screenshot>,
    val shortDescription: String,
    val status: String,
    val thumbnail: String,
    val title: String
)
