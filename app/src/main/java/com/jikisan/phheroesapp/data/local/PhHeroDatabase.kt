package com.jikisan.phheroesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jikisan.phheroesapp.data.local.dao.DatabaseConverter
import com.jikisan.phheroesapp.data.local.dao.HeroDao
import com.jikisan.phheroesapp.data.local.dao.HeroRemoteKeysDao
import com.jikisan.phheroesapp.domain.model.Hero
import com.jikisan.phheroesapp.domain.model.HeroRemoteKeys

@Database(entities = [Hero::class, HeroRemoteKeys::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class PhHeroDatabase: RoomDatabase() {

    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeysDao(): HeroRemoteKeysDao
}