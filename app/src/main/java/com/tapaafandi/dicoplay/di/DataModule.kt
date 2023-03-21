package com.tapaafandi.dicoplay.di

import com.tapaafandi.dicoplay.data.repository.OfflineFirstUserDataRepository
import com.tapaafandi.dicoplay.data.util.ConnectivityManagerNetworkMonitor
import com.tapaafandi.dicoplay.data.util.NetworkMonitor
import com.tapaafandi.dicoplay.domain.repository.UserDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor
    ): NetworkMonitor

    @Binds
    fun bindsUserDataRepository(
        userDataRepository: OfflineFirstUserDataRepository,
    ): UserDataRepository
}