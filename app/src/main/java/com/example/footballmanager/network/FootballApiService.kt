package com.example.footballmanager.network

import retrofit2.http.GET
import retrofit2.http.Query

interface FootballApiService {

    @GET("fixtures")
    suspend fun getFixtures(
        @Query("date") date: String
    ): FixtureDataWrapper
}
