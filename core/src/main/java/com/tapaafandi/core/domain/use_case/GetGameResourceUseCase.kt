package com.tapaafandi.core.domain.use_case

import com.tapaafandi.core.domain.model.Game
import com.tapaafandi.core.domain.model.UserData
import com.tapaafandi.core.domain.model.UserGameResource
import com.tapaafandi.core.domain.model.mapToUserGameResource
import com.tapaafandi.core.domain.repository.GameRepository
import com.tapaafandi.core.domain.repository.UserDataRepository
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