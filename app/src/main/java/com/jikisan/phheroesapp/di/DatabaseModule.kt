package com.jikisan.phheroesapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jikisan.phheroesapp.data.local.PhHeroDatabase
import com.jikisan.phheroesapp.data.repository.LocalDataSourceImpl
import com.jikisan.phheroesapp.domain.repository.LocalDataSource
import com.jikisan.phheroesapp.util.Constants.PH_HERO_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): PhHeroDatabase {
        return Room.databaseBuilder(
            context,
            PhHeroDatabase::class.java,
            PH_HERO_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(
        database: PhHeroDatabase
    ): LocalDataSource {
        return LocalDataSourceImpl(
            phHeroDatabase = database
        )
    }
}