package com.jikisan.phheroesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jikisan.phheroesapp.data.local.dao.HeroDao
import com.jikisan.phheroesapp.domain.model.Hero

@Database(entities = [Hero::class], version = 1)
abstract class PhHeroDatabase: RoomDatabase() {

    abstract fun heroDao(): HeroDao
}