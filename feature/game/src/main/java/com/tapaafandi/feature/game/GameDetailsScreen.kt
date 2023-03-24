package com.tapaafandi.feature.game

import android.net.Uri
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.tapaafandi.core.designsystem.component.DicoplayToggleButton
import com.tapaafandi.core.designsystem.icon.DicoplayIcon
import com.tapaafandi.core.domain.model.BookmarkableGame
import com.tapaafandi.core.domain.model.Game
import com.tapaafandi.core.uitls.launchCustomChromeTab

@Composable
fun GameDetailsRoute(
    onBackClick: () -> Unit,
    viewModel: GameDetailsViewModel = hiltViewModel()
) {
    val gameUiState: GameDetailsUiState by viewModel.gameUiState.collectAsStateWithLifecycle()
    GameDetailsScreen(
        gameUiState = gameUiState,
        onBookmarkChanged = viewModel::bookmarkGame,
        onBackClick = onBackClick
    )
}

@Composable
fun GameDetailsScreen(
    gameUiState: GameDetailsUiState,
    onBookmarkChanged: (Int, Boolean) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val backgroundColor = MaterialTheme.colorScheme.background.toArgb()

    when (gameUiState) {
        is GameDetailsUiState.Error -> {}
        is GameDetailsUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is GameDetailsUiState.Success -> {
            val gameUrl by remember {
                mutableStateOf(Uri.parse(gameUiState.bookmarkableGame.game.gameUrl))
            }
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
                }
                item {
                    GameDetailsToolbar(
                        onBackClick = onBackClick,
                        onVisitGameClick = {
                            launchCustomChromeTab(context, gameUrl, backgroundColor)
                        }
                    )
                }
                item {
                    GameImage(gameImageUrl = gameUiState.bookmarkableGame.game.thumbnail)
                    Spacer(modifier = Modifier.height(24.dp))
                    Row {
                        GameTitle(
                            gameTitle = gameUiState.bookmarkableGame.game.title,
                            modifier = Modifier.fillMaxWidth(.8f)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        BookmarkButton(
                            isBookmarked = gameUiState.bookmarkableGame.isBookmarked,
                            onCLick = {
                                onBookmarkChanged(
                                    gameUiState.bookmarkableGame.game.gameId,
                                    !gameUiState.bookmarkableGame.isBookmarked
                                )
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    gameUiState.bookmarkableGame.game.description.let { description ->
                        if (description != null) {
                            ExpandingGameDescription(text = description)
                        } else {
                            ExpandingGameDescription(text = gameUiState.bookmarkableGame.game.shortDescription)
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    GameInformation(
                        gamePublisher = gameUiState.bookmarkableGame.game.publisher,
                        gameDeveloper = gameUiState.bookmarkableGame.game.developer,
                        gameGenre = gameUiState.bookmarkableGame.game.genre,
                        gamePlatform = gameUiState.bookmarkableGame.game.platform,
                        gameReleaseDate = gameUiState.bookmarkableGame.game.releaseDate
                    )
                }
            }
        }
    }
}

@Composable
fun GameDetailsToolbar(
    onBackClick: () -> Unit,
    onVisitGameClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = DicoplayIcon.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .clickable { onBackClick() }
                .indication(
                interactionSource = MutableInteractionSource(),
                indication = null
            )
        )
        OutlinedButton(onClick = onVisitGameClick) {
            Text(text = stringResource(R.string.play_now))
        }
    }
}

@Composable
fun GameTitle(
    gameTitle: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = gameTitle,
        style = MaterialTheme.typography.headlineMedium,
        modifier = modifier
    )
}

@Composable
fun GameImage(
    gameImageUrl: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = gameImageUrl,
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
    )
}

@Composable
fun GameInformation(
    gamePublisher: String,
    gameDeveloper: String,
    gameGenre: String,
    gamePlatform: String,
    gameReleaseDate: String
) {
    Column {
        Text(
            text = stringResource(R.string.information),
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        GameInformationItem(title = stringResource(R.string.platform), information = gamePlatform)
        GameInformationItem(title = stringResource(R.string.genre), information = gameGenre)
        GameInformationItem(title = stringResource(R.string.publisher), information = gamePublisher)
        GameInformationItem(title = stringResource(R.string.developer), information = gameDeveloper)
        GameInformationItem(title = stringResource(R.string.release_date), information = gameReleaseDate)

    }
}

@Composable
fun ExpandingGameDescription(
    modifier: Modifier = Modifier,
    text: String
) {
    var isExpanded by remember { mutableStateOf(false) }
    val textLayoutResultState = remember { mutableStateOf<TextLayoutResult?>(null) }
    var isClickable by remember { mutableStateOf(false) }
    var finalText by remember { mutableStateOf(text) }


    val textLayoutResult = textLayoutResultState.value
    LaunchedEffect(key1 = textLayoutResultState.value) {
        if (textLayoutResult == null) return@LaunchedEffect

        when {
            isExpanded -> {
                finalText = "$text Show less"
            }
            textLayoutResult.hasVisualOverflow -> {
                val lastCharIndex = textLayoutResult.getLineEnd(5 - 1)
                val showMoreString = "... Show more."
                val adjustedText = text
                    .substring(startIndex = 0, endIndex = lastCharIndex)
                    .dropLast(showMoreString.length)
                    .dropLastWhile { it == ' ' || it == '.'}

                finalText = "$adjustedText$showMoreString"

                isClickable = true
            }
        }
    }

    Text(
        text = finalText,
        maxLines = if (isExpanded) Int.MAX_VALUE else 5,
        onTextLayout = { textLayoutResultState.value = it },
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
            .clickable(
                enabled = isClickable,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { isExpanded = !isExpanded }
            .animateContentSize()
    )
}

@Composable
fun GameInformationItem(
    title: String,
    information: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {
        Text(text = title, style = MaterialTheme.typography.bodyMedium)
        Text(text = information, style = MaterialTheme.typography.bodyMedium)
    }
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

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun GameDetailsScreenPreview() {
    GameDetailsScreen(
        gameUiState = GameDetailsUiState.Success(
            bookmarkableGame = BookmarkableGame(
                game = Game(
                    id = 0,
                    gameId = 0,
                    description = """
                        "Dauntless is a free-to-play, co-op action RPG developed by independent studio Phoenix Labs — a studio made of of veteran developers from Bioware, Riot, Capcom, and Blizzard. Set in a science-fantasy world, Dauntless places players in the role of elite warriors called Slayers. These Slayers protect humanity from Behemoths that are consuming the land following a cataclysmic event that turned the landscape into ever-changing, floating islands.
                        
                        The gameplay may remind players of Monster Hunter, or perhaps a cheerier version of Shadow of the Colossus, where the goal is to defeat massive creates in an vast landscape.
                        
                        Dauntless is playable solo, although it is designed with co-op play in mind. It boasts a variety of unique encounters and rewards players with items that will allow them to upgrade weapons and armor — enabling them to become even stronger warriors.
                    """.trimIndent(),
                    freeToGameProfileUrl = "",
                    gameUrl = "",
                    genre = "MMORPG",
                    platform = "Windows",
                    publisher = "Phoenix Labs",
                    releaseDate = "2019-05-21",
                    shortDescription = "",
                    thumbnail = "https://www.freetogame.com/g/1/thumbnail.jpg",
                    title = "Dauntless",
                    developer = "Phoenix Labs, Iron Galaxy"
                ), isBookmarked = false

            )
        ),
        onBookmarkChanged = { _: Int, _: Boolean -> },
        onBackClick = {}
    )
}

