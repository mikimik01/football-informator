package com.example.footballmanager.network

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FootballApi {

    @Provides
    fun provideBaseUrl(): String = "https://v3.football.api-sports.io"

    @Provides
    fun provideClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(client)
        .build()

    /*val retrofitService: FootballApiService by lazy {
        retrofit.create(FootballApiService::class.java)
    }*/

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): FootballApiService =
        retrofit.create(FootballApiService::class.java)
}