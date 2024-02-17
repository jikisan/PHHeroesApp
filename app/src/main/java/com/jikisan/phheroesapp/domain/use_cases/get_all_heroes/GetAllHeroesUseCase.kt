package com.jikisan.phheroesapp.domain.use_cases.get_all_heroes

import androidx.paging.PagingData
import com.jikisan.phheroesapp.data.repository.Repository
import com.jikisan.phheroesapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class GetAllHeroesUseCase(
    private val repository: Repository
) {

    operator fun invoke(): Flow<PagingData<Hero>>{
        return repository.getAllHeroes()
    }
}