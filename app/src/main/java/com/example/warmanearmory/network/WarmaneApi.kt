package com.example.warmanearmory.network

import com.example.warmanearmory.model.CharacterData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface WarmaneApi {
    @GET("character/{name}/{realm}/summary")
    suspend fun getCharacter(
        @Path("name") name: String,
        @Path("realm") realm: String
    ): CharacterData
}

object RetrofitInstance {
    val api: WarmaneApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://armory.warmane.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WarmaneApi::class.java)
    }
}