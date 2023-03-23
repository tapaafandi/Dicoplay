package com.tapaafandi.feature.bookmarks.di

import android.content.Context
import com.tapaafandi.dicoplay.di.BookmarksModuleDependencies
import com.tapaafandi.feature.bookmarks.BookmarksActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [BookmarksModuleDependencies::class]
)
interface BookmarksComponent {
    fun inject(bookmarksActivity: BookmarksActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(bookmarksModuleDependencies: BookmarksModuleDependencies): Builder
        fun build(): BookmarksComponent
    }
}