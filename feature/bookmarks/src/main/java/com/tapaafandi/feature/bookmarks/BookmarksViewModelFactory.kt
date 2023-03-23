package com.tapaafandi.feature.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tapaafandi.core.domain.repository.UserDataRepository
import com.tapaafandi.core.domain.use_case.GetGameResourceUseCase
import javax.inject.Inject

class BookmarksViewModelFactory @Inject constructor(
    private val userDataRepository: UserDataRepository,
    private val getGameResourceUseCase: GetGameResourceUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(BookmarksViewModel::class.java) ->
                BookmarksViewModel(
                    userDataRepository,
                    getGameResourceUseCase
                ) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class ${modelClass.name}")
        }

}