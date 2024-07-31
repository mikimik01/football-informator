package com.example.footballmanager.network

import retrofit2.http.GET
import retrofit2.http.Query
import java.time.LocalDate

interface FootballApiService {

    @GET("fixtures")
    suspend fun getFixtures(
        //@Query("limit") limit: Int, //2 characters
        @Query("date") date: String
    ): FixtureDataWrapper
}
