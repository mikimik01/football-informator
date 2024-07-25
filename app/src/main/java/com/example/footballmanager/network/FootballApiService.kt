package com.example.footballmanager.network

import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

private val BASE_URL =
    "https://v3.football.api-sports.io"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(ScalarsConverterFactory.create())
    .build()

interface FootballApiService {
    @Headers(
        "x-rapidapi-host: v3.football.api-sports.io",
        "x-rapidapi-key: 182fc0d02721df1eca6a1ad83cf310ac")
    @GET("status")
    suspend fun getStatus():String
}

object FootballApi{
    val retrofitService: FootballApiService by lazy {
        retrofit.create(FootballApiService::class.java)
    }
}