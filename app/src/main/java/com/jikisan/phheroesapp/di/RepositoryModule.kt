package com.jikisan.phheroesapp.di

import android.content.Context
import com.jikisan.phheroesapp.data.repository.DataStoreOperationsImpl
import com.jikisan.phheroesapp.data.repository.Repository
import com.jikisan.phheroesapp.domain.repository.DataStoreOperations
import com.jikisan.phheroesapp.domain.use_cases.search_heroes.SearchHeroesUseCase
import com.jikisan.phheroesapp.domain.use_cases.UseCases
import com.jikisan.phheroesapp.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.jikisan.phheroesapp.domain.use_cases.get_selected_hero.GetSelectedHeroUseCase
import com.jikisan.phheroesapp.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.jikisan.phheroesapp.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context
    ): DataStoreOperations{
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun providesUseCases(repository: Repository): UseCases{
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository),
            getAllHeroesUseCase = GetAllHeroesUseCase(repository),
            searchHeroesUseCase = SearchHeroesUseCase(repository),
            getSelectedHeroUseCase = GetSelectedHeroUseCase(repository)
        )
    }
}