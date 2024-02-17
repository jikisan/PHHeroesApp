package com.jikisan.phheroesapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jikisan.phheroesapp.data.local.PhHeroDatabase
import com.jikisan.phheroesapp.data.paging_source.HeroRemoteMediator
import com.jikisan.phheroesapp.data.remote.PhHeroesApi
import com.jikisan.phheroesapp.domain.model.Hero
import com.jikisan.phheroesapp.domain.repository.RemoteDataSource
import com.jikisan.phheroesapp.util.Constants.ITEM_PER_PAGE
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val phHeroesApi: PhHeroesApi,
    private val phHeroDatabase: PhHeroDatabase
): RemoteDataSource {

    private val heroDao = phHeroDatabase.heroDao()

    override fun getAllHeroes(): Flow<PagingData<Hero>> {
        val pagingSourceFactory = { heroDao.getAllHeroes() }
        return Pager(
            config = PagingConfig(pageSize = ITEM_PER_PAGE),
            remoteMediator = HeroRemoteMediator(
                phHeroesApi = phHeroesApi,
                phHeroDatabase = phHeroDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchHeroes(): Flow<PagingData<Hero>> {
        TODO("Not yet implemented")
    }
}