package com.example.footballmanager.data

import com.example.footballmanager.data.entities.Match

interface MatchesDataSource {
    suspend fun fetchAndCacheMatchesByDate(date: String): List<Match>
    suspend fun getCachedMatches(): List<Match>
    suspend fun fetchMatchesByLiveNow(): List<Match>
}