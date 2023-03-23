package com.tapaafandi.core.domain.model

data class UserGameResource internal constructor(
    val id: Int,
    val gameId: Int,
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
    val isSaved: Boolean
) {
    constructor(game: Game, uerData: UserData): this(
        id = game.id,
        gameId = game.gameId,
        developer = game.developer,
        freeToGameProfileUrl = game.freeToGameProfileUrl,
        gameUrl = game.gameUrl,
        genre = game.genre,
        platform = game.platform,
        publisher = game.publisher,
        releaseDate = game.releaseDate,
        shortDescription = game.shortDescription,
        thumbnail = game.thumbnail,
        title = game.title,
        isSaved = uerData.bookmarkedGames.contains(game.gameId)
    )
}

fun List<Game>.mapToUserGameResource(userData: UserData): List<UserGameResource> {
    return map { UserGameResource(it, userData) }
}
