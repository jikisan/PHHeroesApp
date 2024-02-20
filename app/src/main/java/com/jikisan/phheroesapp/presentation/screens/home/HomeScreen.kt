package com.jikisan.phheroesapp.presentation.screens.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.jikisan.phheroesapp.presentation.common.ListContent
import com.jikisan.phheroesapp.presentation.components.RatingWidget
import com.jikisan.phheroesapp.ui.theme.EXTRA_LARGE_PADDING
import com.jikisan.phheroesapp.ui.theme.EXTRA_SMALL_PADDING
import com.jikisan.phheroesapp.ui.theme.LARGE_PADDING
import com.jikisan.phheroesapp.ui.theme.SMALL_PADDING

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@ExperimentalCoilApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeTopBar(onSearchClicked = {})
        },
        content = {
            ListContent(
                heroes = allHeroes,
                navController = navController
            )

            Log.d("HOME_SCREEN", allHeroes.itemSnapshotList.toString())
        }
    )
}

