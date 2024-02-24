package com.jikisan.phheroesapp.domain.use_cases

import com.jikisan.phheroesapp.domain.search_heroes.SearchHeroesUseCase
import com.jikisan.phheroesapp.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.jikisan.phheroesapp.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.jikisan.phheroesapp.domain.use_cases.save_onboarding.SaveOnBoardingUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val getAllHeroesUseCase: GetAllHeroesUseCase,
    val searchHeroesUseCase: SearchHeroesUseCase
)
