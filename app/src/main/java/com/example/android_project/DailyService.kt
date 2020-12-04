package com.example.android_project

import retrofit2.Call
import retrofit2.http.*

interface DailyService {

    @GET("dailyUS")
    fun getAllDaily(): Call<ArrayList<Daily>>

    @POST("dailyUS")
    fun createDaily(@Body() daily: Daily): Call<Daily>

    @PUT("dailyUS/{date}")
    fun SelectFavorite(@Path("date") date: Int): Call<Void>
}