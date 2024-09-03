package com.example.footballmanager.data.network.api

import com.example.footballmanager.data.entities.MatchDataWrapper
import com.example.footballmanager.data.entities.MatchEventsDataWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballApiService {

    @GET("fixtures")
    suspend fun getMatchesByDate(
        @Query("date") date: String
    ): MatchDataWrapper

    @GET("fixtures")
    suspend fun getMatchesByLiveNow(
        @Query("live") live: String = "all"
    ): MatchDataWrapper

    @GET("fixtures/events")
    suspend fun getMatchEvents(
        @Query("fixture") live: Int
    ): MatchEventsDataWrapper
}
