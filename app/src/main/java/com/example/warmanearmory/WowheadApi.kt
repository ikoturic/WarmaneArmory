package com.example.warmanearmory

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WowheadApi {
    @GET("tooltip/item/{itemId}")
    suspend fun getItemTooltip(@Path("itemId") itemId: String): Response<ResponseBody>
}