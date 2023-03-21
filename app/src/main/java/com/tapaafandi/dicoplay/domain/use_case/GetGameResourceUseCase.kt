package com.tapaafandi.dicoplay.domain.use_case

import com.tapaafandi.dicoplay.domain.model.*
import com.tapaafandi.dicoplay.domain.repository.GameRepository
import com.tapaafandi.dicoplay.domain.repository.UserDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNot
import javax.inject.Inject

class GetGameResourceUseCase @Inject constructor(
    private val gameRepository: GameRepository,
    private val userDataRepository: UserDataRepository
) {
    operator fun invoke(): Flow<List<UserGameResource>> {
        return gameRepository.getTopFreeToPlayGames().mapToUserGameResource(userDataRepository.userData)
    }
}

fun Flow<List<Game>>.mapToUserGameResource(
    userDataStream: Flow<UserData>
): Flow<List<UserGameResource>> {
    return filterNot { it.isEmpty() }
        .combine(userDataStream) { game, userData ->
            game.mapToUserGameResource(userData)
        }
}