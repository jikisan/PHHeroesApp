package com.jikisan.phheroesapp.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.jikisan.phheroesapp.presentation.screens.details.DetailsScreen
import com.jikisan.phheroesapp.presentation.screens.home.HomeScreen
import com.jikisan.phheroesapp.presentation.screens.search.SearchScreen
import com.jikisan.phheroesapp.presentation.screens.splash.SplashScreen
import com.jikisan.phheroesapp.presentation.screens.welcome.WelcomeScreen
import com.jikisan.phheroesapp.util.Constants.DETAILS_ARGUMENT_KEY

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun SetupNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ){

        composable(route = Screen.Splash.route){
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Welcome.route){
            WelcomeScreen(navController = navController)
        }
        composable(route = Screen.Home.route){
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY){
                type = NavType.IntType
            })
        ){
            DetailsScreen(navController = navController)
        }
        composable(route = Screen.Search.route){
            SearchScreen(
                navController = navController
            )
        }
    }
}