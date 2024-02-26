package com.jikisan.phheroesapp.presentation.screens.search

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.jikisan.phheroesapp.presentation.common.ListContent
import com.jikisan.phheroesapp.ui.theme.LARGE_PADDING

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalCoilApi
@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val searchQuery by searchViewModel.searchQuery
    val heroes = searchViewModel.searchedHeroes.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchTopBar(
                text = searchQuery,
                onTextChange = {
                    searchViewModel.updateSearchQuery(query = it)
                },
                onSearchClicked = {
                    searchViewModel.searchHeroes(query = it)
                },
                onCloseClicked = {
                    navController.popBackStack()
                },
            )
        },
        content = {
            ListContent(
                heroes = heroes,
                navController = navController,
                paddingValues = it
            )
        },
        modifier = Modifier
            .padding(0.dp, LARGE_PADDING, 0.dp, 0.dp)
    )
}