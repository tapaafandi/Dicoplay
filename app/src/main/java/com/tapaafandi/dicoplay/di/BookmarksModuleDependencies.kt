package com.tapaafandi.dicoplay.di

import com.tapaafandi.core.domain.repository.UserDataRepository
import com.tapaafandi.core.domain.use_case.GetGameResourceUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@EntryPoint
@InstallIn(SingletonComponent::class)
interface BookmarksModuleDependencies {

    fun provideUserDataRepository(): UserDataRepository

    fun provideGetGameResourceUseCase(): GetGameResourceUseCase
}