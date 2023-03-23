package com.tapaafandi.core.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tapaafandi.core.designsystem.component.DicoplayToggleButton
import com.tapaafandi.core.designsystem.icon.DicoplayIcon
import com.tapaafandi.core.domain.model.UserGameResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameResourceCard(
    userGameResource: UserGameResource,
    isBookmarked: Boolean,
    onToggleBookmark: () -> Unit,
    onGameClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        onClick = {
            onGameClick(userGameResource.gameId)
        },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Column {
            GameResourceThumbnailImage(gameThumbnailUrl = userGameResource.thumbnail)
            Box(
                modifier = modifier.padding(horizontal = 4.dp, vertical = 12.dp)
            ) {
                Column {
                    Row {
                        Column(
                            modifier = Modifier.fillMaxWidth(.8f)
                        ) {
                            GameResourceTitle(
                                gameResourceTitle = userGameResource.title
                            )
                            GameResourceGenre(gameResourceGenre = userGameResource.genre)
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        BookmarkButton(
                            isBookmarked = isBookmarked,
                            onCLick = onToggleBookmark
                        )
                    }
                    GameResourceShortDescription(gameResourceShortDescription = userGameResource.shortDescription)
                }
            }
        }
    }
}

@Composable
fun GameResourceThumbnailImage(
    gameThumbnailUrl: String
) {
    AsyncImage(
        model = gameThumbnailUrl,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
    )
}

@Composable
fun GameResourceTitle(
    gameResourceTitle: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = gameResourceTitle,
        style = MaterialTheme.typography.headlineSmall,
        modifier = modifier
    )
}

@Composable
fun GameResourceGenre(
    gameResourceGenre: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = gameResourceGenre,
        style = MaterialTheme.typography.labelMedium,
        modifier = modifier
    )
}

@Composable
fun GameResourceShortDescription(
    gameResourceShortDescription: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = gameResourceShortDescription,
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
    )
}

@Composable
fun BookmarkButton(
    isBookmarked: Boolean,
    onCLick: () -> Unit,
    modifier: Modifier = Modifier
) {
    DicoplayToggleButton(
        checked = isBookmarked,
        onCheckedChange = { onCLick() },
        modifier = modifier,
        icon = {
            Icon(
                painter = painterResource(id = DicoplayIcon.BookmarkBorder),
                contentDescription = null
            )
        },
        checkedIcon = {
            Icon(painter = painterResource(id = DicoplayIcon.Bookmark), contentDescription = null)
        }
    )
}