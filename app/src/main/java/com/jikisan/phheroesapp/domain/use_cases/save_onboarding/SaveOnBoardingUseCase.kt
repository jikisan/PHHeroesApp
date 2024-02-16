package com.jikisan.phheroesapp.domain.use_cases.save_onboarding

import com.jikisan.phheroesapp.data.repository.Repository

class SaveOnBoardingUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(completed: Boolean){
        repository.saveOnBoardingState(completed = completed)
    }
}