package com.example.footballmanager.network

import com.example.footballmanager.network.structures.FixtureDataWrapper
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
