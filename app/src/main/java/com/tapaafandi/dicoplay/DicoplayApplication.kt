package com.tapaafandi.dicoplay

import android.app.Application
import com.tapaafandi.core.sync.initializers.Sync
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DicoplayApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Sync.initialize(context = this)
    }
}