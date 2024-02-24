package com.jikisan.phheroesapp.data.repository

import com.jikisan.phheroesapp.data.local.PhHeroDatabase
import com.jikisan.phheroesapp.domain.model.Hero
import com.jikisan.phheroesapp.domain.repository.LocalDataSource

class LocalDataSourceImpl(phHeroDatabase: PhHeroDatabase): LocalDataSource {

    private val heroDao = phHeroDatabase.heroDao()

    override suspend fun getSelectedHero(heroId: Int): Hero {
        return heroDao.getSelectedHero(heroId = heroId)
    }

}