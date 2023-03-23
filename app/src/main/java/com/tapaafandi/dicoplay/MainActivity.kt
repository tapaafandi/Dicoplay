package com.tapaafandi.dicoplay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.core.view.WindowCompat
import com.tapaafandi.core.data.util.NetworkMonitor
import com.tapaafandi.core.designsystem.theme.DicoplayTheme
import com.tapaafandi.dicoplay.ui.DicoplayApp
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            DicoplayTheme {
                DicoplayApp(
                    windowSizeClass = calculateWindowSizeClass(this),
                    networkMonitor = networkMonitor
                )
            }
        }
    }
}