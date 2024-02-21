package com.jikisan.phheroesapp.di

import com.jikisan.phheroesapp.data.remote.PhHeroesApi
import com.jikisan.phheroesapp.util.Constants.BASE_URL
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val contentType = MediaType.get("application/json")

    @OptIn(ExperimentalSerializationApi::class)
    val apiResponse: PhHeroesApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PhHeroesApi::class.java)
    }

}