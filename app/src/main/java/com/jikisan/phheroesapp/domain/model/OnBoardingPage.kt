package com.jikisan.phheroesapp.domain.model

import androidx.annotation.DrawableRes
import com.jikisan.phheroesapp.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
){
    object First: OnBoardingPage(
        image = R.drawable.greetings,
        title = "Greetings",
        description = "Are you a PH Hero fan, you are welcome here"
    )
    object Second: OnBoardingPage(
        image = R.drawable.explore,
        title = "Explore",
        description = "Explore your favorite Philippine heroes here"
    )
    object Third: OnBoardingPage(
        image = R.drawable.power,
        title = "Explore",
        description = "Learn more about Philippines heroes"
    )
}

