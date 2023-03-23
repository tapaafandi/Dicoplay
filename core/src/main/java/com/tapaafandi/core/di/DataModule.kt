package com.tapaafandi.core.di

import com.tapaafandi.core.data.repository.OfflineFirstUserDataRepository
import com.tapaafandi.core.data.util.ConnectivityManagerNetworkMonitor
import com.tapaafandi.core.data.util.NetworkMonitor
import com.tapaafandi.core.domain.repository.UserDataRepository
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