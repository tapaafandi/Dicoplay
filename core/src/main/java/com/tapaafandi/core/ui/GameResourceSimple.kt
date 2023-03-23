package com.tapaafandi.core.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tapaafandi.core.domain.model.UserGameResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameResourceSimple(
    userGameResource: UserGameResource,
    onGameClick: (Int) -> Unit,
    gameNumber: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        onClick = {
            onGameClick(userGameResource.gameId)
        },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            GameResourceImage(gameResourceImageUrl = userGameResource.thumbnail)
            Text(text = gameNumber, modifier = Modifier.padding(horizontal = 12.dp), style = MaterialTheme.typography.bodyMedium)
            Column {
                GameResourceSimpleTitle(gameResourceSimpleTitle = userGameResource.title)
                Spacer(modifier = Modifier.height(4.dp))
                GameResourceGenre(gameResourceGenre = userGameResource.genre)
                Spacer(modifier = Modifier.height(8.dp))
                GameResourceSimpleShortDescription(gameResourceShortDescription = userGameResource.shortDescription)
            }
        }
    }
}

@Composable
fun GameResourceImage(
    gameResourceImageUrl: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = gameResourceImageUrl,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(90.dp)
            .clip(RoundedCornerShape(8.dp))
    )
}

@Composable
fun GameResourceSimpleTitle(
    gameResourceSimpleTitle: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = gameResourceSimpleTitle,
        style = MaterialTheme.typography.titleMedium,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun GameResourceSimpleShortDescription(
    gameResourceShortDescription: String
) {
    Text(
        text = gameResourceShortDescription,
        style = MaterialTheme.typography.bodySmall,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}