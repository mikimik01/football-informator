package com.example.footballmanager.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FootballApi {
    private const val BASE_URL =
        "https://v3.football.api-sports.io"

    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(client)
        .build()

    val retrofitService: FootballApiService by lazy {
        retrofit.create(FootballApiService::class.java)
    }
}