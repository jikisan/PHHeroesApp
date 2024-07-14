package com.jikisan.phheroesapp.domain.repository

import androidx.paging.PagingData
import com.jikisan.phheroesapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

interface RemoteDataSource {
    fun getAllHeroes(): Flow<PagingData<Hero>>
    fun searchHeroes(query: String): Flow<PagingData<Hero>>
}