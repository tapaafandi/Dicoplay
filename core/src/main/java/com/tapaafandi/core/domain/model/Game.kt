package com.tapaafandi.core.domain.model

data class Game(
    val id: Int,
    val gameId: Int,
    val description: String? = null,
    val developer: String,
    val freeToGameProfileUrl: String,
    val gameUrl: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    val releaseDate: String,
    val shortDescription: String,
    val thumbnail: String,
    val title: String,
)
