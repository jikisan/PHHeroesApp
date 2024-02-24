package com.jikisan.phheroesapp.presentation.screens.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jikisan.phheroesapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(onSearchClicked: () -> Unit){
    TopAppBar(
        title = {
            Text(
                text = "Explore",
                color = if(isSystemInDarkTheme()) Color.White else Color.Black
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = if(isSystemInDarkTheme()) Color.Black else Color.White
        ),
        actions = {
            IconButton(
                onClick = onSearchClicked
            ) {
                Icon(
                   imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search_icon),
                    tint = if(isSystemInDarkTheme()) Color.White else Color.Black
                )
            }
        }

    )
}

@Composable
@Preview
fun HomeTopBarPreview(){
    HomeTopBar {    }
}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun HomeTopBarDarkPreview(){
    HomeTopBar {    }
}
