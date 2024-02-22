package com.jikisan.phheroesapp.di

import androidx.paging.ExperimentalPagingApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.jikisan.phheroesapp.data.local.PhHeroDatabase
import com.jikisan.phheroesapp.data.remote.PhHeroesApi
import com.jikisan.phheroesapp.data.repository.RemoteDataSourceImpl
import com.jikisan.phheroesapp.domain.repository.RemoteDataSource
import com.jikisan.phheroesapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalPagingApi
@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {

        val contentType = MediaType.get("application/json")
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePhHeroesApi(retrofit: Retrofit): PhHeroesApi {
        return retrofit.create(PhHeroesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        phHeroesApi: PhHeroesApi,
        phHeroDatabase: PhHeroDatabase
    ): RemoteDataSource{
        return RemoteDataSourceImpl(
            phHeroesApi = phHeroesApi,
            phHeroDatabase = phHeroDatabase
        )
    }
}