package com.tapaafandi

import android.app.Application
import com.tapaafandi.dicoplay.sync.initializers.Sync
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DicoplayApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Sync.initialize(context = this)
    }
}