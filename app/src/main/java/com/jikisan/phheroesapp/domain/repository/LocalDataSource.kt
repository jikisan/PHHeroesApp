package com.jikisan.phheroesapp.domain.repository

import com.jikisan.phheroesapp.domain.model.Hero

interface LocalDataSource {
    suspend fun getSelectedHero(heroId: Int): Hero
}