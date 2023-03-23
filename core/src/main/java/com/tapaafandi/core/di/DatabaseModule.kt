package com.tapaafandi.core.di

import android.content.Context
import androidx.room.Room
import com.tapaafandi.core.data.local.DicoplayDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesDicoplayDatabase(
        @ApplicationContext context: Context
    ): DicoplayDatabase = Room.databaseBuilder(
        context,
        DicoplayDatabase::class.java,
        "dicoplay-database"
    ).build()
}