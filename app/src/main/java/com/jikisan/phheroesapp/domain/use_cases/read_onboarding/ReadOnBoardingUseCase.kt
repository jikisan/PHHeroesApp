package com.jikisan.phheroesapp.domain.use_cases.read_onboarding

import com.jikisan.phheroesapp.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingUseCase(private val  repository: Repository) {

    operator fun invoke(): Flow<Boolean>{
        return repository.readOnBoardingState()
    }
}