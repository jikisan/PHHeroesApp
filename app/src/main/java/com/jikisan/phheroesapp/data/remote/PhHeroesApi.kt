package com.jikisan.phheroesapp.data.remote

import com.jikisan.phheroesapp.domain.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PhHeroesApi {

    @GET("/boruto/heroes")
    suspend fun getAllHeroes(
        @Query("page") page: Int = 1
    ): ApiResponse

    @GET("/boruto/heroes/search")
    suspend fun searchHeroes(
        @Query("name") name: String
    ): ApiResponse
}