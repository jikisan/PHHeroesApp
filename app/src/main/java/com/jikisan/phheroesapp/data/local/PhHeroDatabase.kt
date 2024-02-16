package com.jikisan.phheroesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.jikisan.phheroesapp.data.local.dao.DatabaseConverter
import com.jikisan.phheroesapp.data.local.dao.HeroDao
import com.jikisan.phheroesapp.data.local.dao.HeroRemoteKeyDao
import com.jikisan.phheroesapp.domain.model.Hero
import com.jikisan.phheroesapp.domain.model.HeroRemoteKey

@Database(entities = [Hero::class, HeroRemoteKey::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class PhHeroDatabase: RoomDatabase() {

    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeyDao
}