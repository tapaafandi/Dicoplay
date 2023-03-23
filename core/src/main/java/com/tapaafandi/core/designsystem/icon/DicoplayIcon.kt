package com.tapaafandi.core.designsystem.icon

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.ui.graphics.vector.ImageVector
import com.tapaafandi.core.R

object DicoplayIcon {
    val VideoGame = R.drawable.ic_videogame
    val VideoGameBorder = R.drawable.ic_videogame_border
    val Bookmark = R.drawable.ic_bookmark
    val BookmarkBorder = R.drawable.ic_bookmark_border
    val Bookmarks = R.drawable.ic_bookmarks
    val BookmarksBorder = R.drawable.ic_bookmarks_border
    val ArrowBack = Icons.Rounded.ArrowBack
}

sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}