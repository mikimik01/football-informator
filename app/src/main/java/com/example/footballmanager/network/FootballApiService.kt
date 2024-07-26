package com.example.footballmanager.network

import com.example.footballmanager.BuildConfig
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val BASE_URL =
    "https://v3.football.api-sports.io"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
    .build()

interface FootballApiService {
    @Headers(
        "x-rapidapi-host: ${BuildConfig.RAPIDAPI_HOST}",
        "x-rapidapi-key: ${BuildConfig.RAPIDAPI_KEY}")
    @GET("fixtures")
    fun getFixtures(
        @Query("last") last: Int //2 characters
    ):Call<FixtureDataWrapper>
}

object FootballApi{
    val retrofitService: FootballApiService by lazy {
        retrofit.create(FootballApiService::class.java)
    }
}