package com.tapaafandi.dicoplay.navigation

import com.tapaafandi.dicoplay.R
import com.tapaafandi.core.designsystem.icon.DicoplayIcon
import com.tapaafandi.core.designsystem.icon.Icon

enum class TopLevelDestination(
    val selectedIcon: Icon,
    val unselectedIcon: Icon,
    val iconTextId: Int,
    val titleTextId: Int
) {
    HOME(
        selectedIcon = Icon.DrawableResourceIcon(DicoplayIcon.VideoGame),
        unselectedIcon = Icon.DrawableResourceIcon(DicoplayIcon.VideoGameBorder),
        iconTextId = R.string.home,
        titleTextId = R.string.app_name
    ),
    BOOKMARKS(
        selectedIcon = Icon.DrawableResourceIcon(DicoplayIcon.Bookmarks),
        unselectedIcon = Icon.DrawableResourceIcon(DicoplayIcon.BookmarksBorder),
        iconTextId = R.string.saved,
        titleTextId = R.string.saved
    )
}