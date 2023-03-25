package com.tapaafandi.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tapaafandi.core.data.local.dao.GameDao
import com.tapaafandi.core.data.local.entities.GameEntity

@Database(
    entities = [
        GameEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class DicoplayDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao
}