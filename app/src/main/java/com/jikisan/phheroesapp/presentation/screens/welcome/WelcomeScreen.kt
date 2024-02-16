package com.jikisan.phheroesapp.presentation.screens.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.HorizontalPageIndicator
import androidx.wear.compose.material.PageIndicatorState
import com.jikisan.phheroesapp.R
import com.jikisan.phheroesapp.domain.model.OnBoardingPage
import com.jikisan.phheroesapp.navigation.Screen
import com.jikisan.phheroesapp.ui.theme.EXTRA_LARGE_PADDING
import com.jikisan.phheroesapp.ui.theme.PAGING_INDICATOR_SPACING
import com.jikisan.phheroesapp.ui.theme.PAGING_INDICATOR_WIDTH
import com.jikisan.phheroesapp.ui.theme.Purple700
import com.jikisan.phheroesapp.ui.theme.SMALL_PADDING
import com.jikisan.phheroesapp.util.Constants.LAST_ON_BOARDING_PAGE
import com.jikisan.phheroesapp.util.Constants.ON_BOARDING_PAGE_COUNT

@ExperimentalFoundationApi
@Composable
fun WelcomeScreen(
    navController: NavHostController,
    welcomeViewModel: WelcomeViewModel = hiltViewModel()

) {
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { ON_BOARDING_PAGE_COUNT }
    )

    val pageIndicatorState: PageIndicatorState = remember {
        object : PageIndicatorState{
            override val pageCount: Int
                get() = pagerState.pageCount
            override val pageOffset: Float
                get() = pagerState.getOffsetFractionForPage(pagerState.currentPage)
            override val selectedPage: Int
                get() = pagerState.currentPage

        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        ){
        HorizontalPager(
            modifier = Modifier.weight(10f),
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) {position ->
            PagerScreen(onBoardingPage = pages[position])
        }
        FinishButton(
            modifier = Modifier.weight(1f),
            pagerState = pagerState
        ) {
            navController.popBackStack()
            navController.navigate(Screen.Home.route)
            welcomeViewModel.saveOnBoardingState(completed = true)
        }
        HorizontalPageIndicator(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally),
            pageIndicatorState = pageIndicatorState,
            selectedColor = Purple700,
            unselectedColor = Color.DarkGray,
            indicatorSize = PAGING_INDICATOR_WIDTH,
            spacing = PAGING_INDICATOR_SPACING,

        )

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FinishButton(
    modifier: Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
){
    Row (
        modifier = Modifier
            .padding(horizontal = EXTRA_LARGE_PADDING),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ){
       AnimatedVisibility(
           modifier = Modifier
               .fillMaxWidth(),
           visible = pagerState.currentPage == LAST_ON_BOARDING_PAGE,
       ) {
           Button(
               onClick = onClick
           ){
               Text(text = "Finish")
           }
       }
    }
}

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage){
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f),
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = stringResource(R.string.on_boarding_image)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = SMALL_PADDING),
            text = onBoardingPage.title,
            color = Color.White,
            fontSize = MaterialTheme.typography.displayMedium.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = EXTRA_LARGE_PADDING)
                .padding(top = SMALL_PADDING),
            text = onBoardingPage.description,
            color = Color.White,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center

        )
    }
}
