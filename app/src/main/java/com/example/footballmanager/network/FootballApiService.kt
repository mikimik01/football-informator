package com.example.footballmanager.network

import com.example.footballmanager.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface FootballApiService {
    @Headers(
        "x-rapidapi-host: ${BuildConfig.RAPIDAPI_HOST}",
        "x-rapidapi-key: ${BuildConfig.RAPIDAPI_KEY}"
    )
    @GET("fixtures")
    fun getFixtures(
        @Query("last") last: Int //2 characters
    ): Call<FixtureDataWrapper>
}
