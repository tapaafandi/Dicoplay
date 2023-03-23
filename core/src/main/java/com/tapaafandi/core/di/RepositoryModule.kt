package com.tapaafandi.core.di

import com.tapaafandi.core.data.network.DicoplayNetworkDataSource
import com.tapaafandi.core.data.network.retrofit.RetrofitDicoplayNetwork
import com.tapaafandi.core.data.repository.OfflineFirstGameRepository
import com.tapaafandi.core.domain.repository.GameRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsGameRepository(
        offlineFirstGameRepository: OfflineFirstGameRepository
    ): GameRepository

    @Binds
    fun bindsDicoplayNetworkDataSource(
        retrofitDicoplayNetwork: RetrofitDicoplayNetwork
    ): DicoplayNetworkDataSource
}