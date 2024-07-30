package com.example.footballmanager.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballApiService {

    @GET("fixtures")
    fun getFixtures(
        @Query("last") last: Int //2 characters
    ): Call<FixtureDataWrapper>
}
