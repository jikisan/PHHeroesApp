

package com.jikisan.phheroesapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.paging.LoadState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.wear.compose.material.ContentAlpha
import com.jikisan.phheroesapp.R
import com.jikisan.phheroesapp.domain.model.Hero
import com.jikisan.phheroesapp.presentation.screens.home.HomeViewModel
import com.jikisan.phheroesapp.ui.theme.DarkGray
import com.jikisan.phheroesapp.ui.theme.LightGray
import com.jikisan.phheroesapp.ui.theme.NETWORK_ERROR_ICON_HEIGHT
import com.jikisan.phheroesapp.ui.theme.SMALL_PADDING
import java.net.ConnectException
import java.net.SocketTimeoutException

@ExperimentalMaterialApi
@Composable
fun EmptyScreen(
    error: LoadState.Error? = null,
    heroes: LazyPagingItems<Hero>? = null
) {
    var message by remember { mutableStateOf("Find your Favorite Hero!")}
    var icon by remember { mutableStateOf(R.drawable.search_document) }

    if (error != null) {
        message = parseErrorMessage(error = error)
        icon = R.drawable.network_error
    }

    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) ContentAlpha.disabled else 0f,
        animationSpec = tween(
            durationMillis = 1000
        ), label = ""
    )
    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    EmptyContent(
        alphaAnim = alphaAnim,
        icon = icon,
        message = message,
        heroes = heroes,
        error = error
    )
}

@ExperimentalMaterialApi
@Composable
fun EmptyContent(
    alphaAnim: Float,
    icon: Int,
    message: String,
    error: LoadState.Error? = null,
    heroes: LazyPagingItems<Hero>? = null
) {

    var isRefreshing by remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(isRefreshing, { heroes?.refresh() })
    Box(
        Modifier
            .pullRefresh(pullRefreshState)
            .background(if(isSystemInDarkTheme()) Color.Black else Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(NETWORK_ERROR_ICON_HEIGHT)
                    .alpha(alpha = alphaAnim),
                painter = painterResource(id = icon),
                contentDescription = stringResource(R.string.network_error_icon),
                tint = if (isSystemInDarkTheme()) LightGray else DarkGray
            )
            Text(
                modifier = Modifier
                    .padding(top = SMALL_PADDING)
                    .alpha(alpha = alphaAnim),
                text = message,
                color = if (isSystemInDarkTheme()) LightGray else DarkGray,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize
            )
        }

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            Modifier.align(Alignment.TopCenter))
    }
}

fun parseErrorMessage(error: LoadState.Error): String {
    return when (error.error) {
        is SocketTimeoutException -> {
            "Server Unavailable"
        }
        is ConnectException -> {
            "Internet Unavailable"
        }
        else -> {
            "Unknown Error."
        }
    }
}

@ExperimentalMaterialApi
@Composable
@Preview(showBackground = true)
fun EmptyScreenPreview() {
    EmptyContent(
        alphaAnim = ContentAlpha.disabled,
        icon = R.drawable.network_error,
        message = "Internet Unavailable."
    )
}

@ExperimentalMaterialApi
@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun EmptyScreenDarkPreview() {
    EmptyContent(
        alphaAnim = ContentAlpha.disabled,
        icon = R.drawable.network_error,
        message = "Internet Unavailable."
    )
}