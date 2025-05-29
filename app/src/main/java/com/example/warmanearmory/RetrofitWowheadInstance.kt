package com.example.warmanearmory

import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.Retrofit
import retrofit2.http.GET


object RetrofitWowheadInstance {
    private const val BASE_URL = "https://www.wowhead.com/"

    val api: WowheadApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(WowheadApi::class.java)
    }
}