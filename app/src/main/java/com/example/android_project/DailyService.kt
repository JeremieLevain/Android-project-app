package com.example.android_project

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface DailyService {

    @GET("dailyUS")
    fun getAllDaily(): Call<ArrayList<Daily>>

    @POST("dailyUS")
    fun createBook(@Body() daily: Daily): Call<Daily>
}