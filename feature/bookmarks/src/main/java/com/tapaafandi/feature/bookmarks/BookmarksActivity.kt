package com.tapaafandi.feature.bookmarks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.tapaafandi.core.designsystem.theme.DicoplayTheme
import com.tapaafandi.dicoplay.di.BookmarksModuleDependencies
import com.tapaafandi.feature.bookmarks.di.DaggerBookmarksComponent
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class BookmarksActivity : ComponentActivity() {

    @Inject
    lateinit var factory: BookmarksViewModelFactory
    private val viewModel: BookmarksViewModel by viewModels {
        factory
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerBookmarksComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    this,
                    BookmarksModuleDependencies::class.java
                )
            ).build().inject(this)
        super.onCreate(savedInstanceState)

        setContent {
            DicoplayTheme {
                BookmarksRoute(viewModel = viewModel, onGameCLick = {})
            }
        }
    }
}