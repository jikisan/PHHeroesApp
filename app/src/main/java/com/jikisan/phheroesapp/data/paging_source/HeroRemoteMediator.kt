package com.jikisan.phheroesapp.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.jikisan.phheroesapp.data.local.PhHeroDatabase
import com.jikisan.phheroesapp.data.remote.PhHeroesApi
import com.jikisan.phheroesapp.domain.model.Hero
import com.jikisan.phheroesapp.domain.model.HeroRemoteKeys
import javax.inject.Inject

@ExperimentalPagingApi
class HeroRemoteMediator @Inject constructor(
    private val phHeroesApi: PhHeroesApi,
    private val phHeroDatabase: PhHeroDatabase
): RemoteMediator<Int, Hero>() {

    private val heroDao = phHeroDatabase.heroDao()
    private val heroRemoteKeysDao = phHeroDatabase.heroRemoteKeysDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Hero>): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeysClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1)?:1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeysForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeysForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = phHeroesApi.getAllHeroes(page = page)

            if(response.heroes.isNotEmpty()){
                phHeroDatabase.withTransaction {
                    if(loadType == LoadType.REFRESH){
                        heroDao.deleteAllHeroes()
                        heroRemoteKeysDao.deleteAllRemoteKeys()
                    }
                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val keys = response.heroes.map {hero ->
                        HeroRemoteKeys(
                            id = hero.id,
                            prevPage = prevPage,
                            nextPage = nextPage
                        )
                    }
                    heroRemoteKeysDao.addAllRemoteKeys(heroRemoteKey = keys)
                    heroDao.addHereos(heroes = response.heroes)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)

        }catch (e: Exception){
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeysClosestToCurrentPosition(
        state: PagingState<Int, Hero>
    ): HeroRemoteKeys? {
        return state.anchorPosition?.let {position ->
            state.closestItemToPosition(position)?.id?.let { id->
                heroRemoteKeysDao.getRemoteKeys(heroId = id)
            }
        }

    }

    private suspend fun getRemoteKeysForFirstItem(
        state: PagingState<Int, Hero>
    ): HeroRemoteKeys? {
        return state.pages.firstOrNull() { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { hero ->
                heroRemoteKeysDao.getRemoteKeys(heroId = hero.id)
            }
    }

    private suspend fun getRemoteKeysForLastItem(
        state: PagingState<Int, Hero>
    ): HeroRemoteKeys? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { hero ->
                heroRemoteKeysDao.getRemoteKeys(heroId = hero.id)
            }
    }

}