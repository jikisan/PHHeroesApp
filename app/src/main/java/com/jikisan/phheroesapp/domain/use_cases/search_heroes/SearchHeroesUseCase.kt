package com.jikisan.phheroesapp.domain.use_cases.search_heroes

import androidx.paging.PagingData
import com.jikisan.phheroesapp.data.repository.Repository
import com.jikisan.phheroesapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class SearchHeroesUseCase(
    private val repository: Repository
) {
    operator fun invoke(query: String): Flow<PagingData<Hero>> {
        return repository.searchHeroes(query = query)
    }
}