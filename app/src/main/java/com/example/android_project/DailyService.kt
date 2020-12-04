package com.example.android_project

import retrofit2.Call
import retrofit2.http.*

/**
 * Interface DailyService
 * List of HTTP method to use with the API on Clever Cloud
 */
interface DailyService {

    @GET("dailyUS")
    fun getAllDaily(): Call<ArrayList<Daily>>

    @PUT("dailyUS/{date}")
    fun SelectFavorite(@Path("date") date: Int): Call<Void>
}