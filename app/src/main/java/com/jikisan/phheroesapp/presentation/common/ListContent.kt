package com.jikisan.phheroesapp.presentation.common

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.wear.compose.material.ContentAlpha
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.jikisan.phheroesapp.R
import com.jikisan.phheroesapp.domain.model.Hero
import com.jikisan.phheroesapp.navigation.Screen
import com.jikisan.phheroesapp.ui.theme.HERO_ITEM_HEIGHT
import com.jikisan.phheroesapp.ui.theme.LARGE_PADDING
import com.jikisan.phheroesapp.ui.theme.MEDIUM_PADDING
import com.jikisan.phheroesapp.util.Constants.BASE_URL
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.itemKey
import com.jikisan.phheroesapp.presentation.components.RatingWidget
import com.jikisan.phheroesapp.presentation.components.ShimmerEffect
import com.jikisan.phheroesapp.ui.theme.SMALL_PADDING

@ExperimentalCoilApi
@Composable
fun ListContent(
    heroes: LazyPagingItems<Hero>,
    navController: NavHostController
) {
    val result = handlePagingResult(heroes = heroes)

    if (result) {
        LazyColumn(
            contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING),
            modifier = Modifier
                .background(color = if(isSystemInDarkTheme()) Color.Black else Color.White)
        ) {
            items(
                count = heroes.itemCount,
                key = heroes.itemKey()

            ) { index ->
                val hero =  heroes.get(index)
                hero?.let {
                    HeroItem(hero = it, navController = navController)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun handlePagingResult(
    heroes: LazyPagingItems<Hero>
): Boolean {
    heroes.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }
            error != null -> {
                EmptyScreen(error = error, heroes = heroes)
                false
            }
            heroes.itemCount < 1 -> {
                EmptyScreen()
                false
            }
            else -> true
        }
    }
}

@ExperimentalCoilApi
@Composable
fun HeroItem(
    hero: Hero,
    navController: NavHostController
){
    val painter = rememberImagePainter(data = "$BASE_URL${hero.image}"){
        placeholder(R.drawable.placeholder)
        error(R.drawable.placeholder)
    }

    Box(
        modifier = Modifier
            .height(HERO_ITEM_HEIGHT)
            .clickable {
                navController.navigate(Screen.Details.passHeroId(heroId = hero.id))
            },
        contentAlignment = Alignment.BottomStart
    ){
        Surface (shape = RoundedCornerShape(size = LARGE_PADDING)) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentDescription = stringResource(R.string.hero_image),
                contentScale = ContentScale.Crop
            )
        }
        Surface (
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(
                bottomStart = LARGE_PADDING,
                bottomEnd = LARGE_PADDING
            )
        ){
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = MEDIUM_PADDING)
            ){
                Text(
                    text = hero.name,
                    color = Color.White,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = hero.about,
                    color = Color.White.copy(alpha = ContentAlpha.medium),
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Row (
                    modifier = Modifier
                        .padding(top = SMALL_PADDING),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    RatingWidget(
                        modifier = Modifier.padding(end = SMALL_PADDING),
                        rating = hero.rating)
                    Text(
                        text = "${hero.rating}",
                        textAlign = TextAlign.Center,
                        color = Color.White.copy(alpha = ContentAlpha.medium),
                    )
                }
            }
        }
    }
}


@ExperimentalCoilApi
@Preview
@Composable
fun HeroItemPreview() {
    HeroItem(
        hero = Hero(
            id = 1,
            name = "Sasuke",
            image = "",
            about = "Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur. Quis aute iure reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint obcaecat cupiditat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            rating = 3.5,
            power = 100,
            month = "",
            day = "",
            family = listOf(),
            abilities = listOf(),
            natureTypes = listOf()

        ),
        navController = rememberNavController())
}
