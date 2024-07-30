package com.example.footballmanager.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FootballApi {
    private const val BASE_URL =
        "https://v3.football.api-sports.io"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    val retrofitService: FootballApiService by lazy {
        retrofit.create(FootballApiService::class.java)
    }
}