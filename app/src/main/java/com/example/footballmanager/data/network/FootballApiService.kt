package com.example.footballmanager.data.network

import com.example.footballmanager.data.entities.FixtureDataWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballApiService {

    @GET("fixtures")
    suspend fun getFixturesByDate(
        @Query("date") date: String
    ): FixtureDataWrapper

    @GET("fixtures")
    suspend fun getFixturesByLiveNow(
        @Query("live") live:String = "all"
    ): FixtureDataWrapper
}
