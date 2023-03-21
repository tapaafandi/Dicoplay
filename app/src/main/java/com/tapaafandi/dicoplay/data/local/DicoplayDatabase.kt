package com.tapaafandi.dicoplay.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tapaafandi.dicoplay.data.local.dao.GameDao
import com.tapaafandi.dicoplay.data.local.entities.GameEntity

@Database(
    entities = [
        GameEntity::class
    ],
    version = 1
)
abstract class DicoplayDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao
}