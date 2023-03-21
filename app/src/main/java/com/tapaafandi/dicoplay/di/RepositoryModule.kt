package com.tapaafandi.dicoplay.di

import com.tapaafandi.dicoplay.data.network.DicoplayNetworkDataSource
import com.tapaafandi.dicoplay.data.network.retrofit.RetrofitDicoplayNetwork
import com.tapaafandi.dicoplay.data.repository.OfflineFirstGameRepository
import com.tapaafandi.dicoplay.domain.repository.GameRepository
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