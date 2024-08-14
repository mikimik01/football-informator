package com.example.footballmanager.data

import com.example.footballmanager.data.entities.Match
import com.example.footballmanager.data.entities.MatchEvents

interface MatchesDataSource {
    suspend fun fetchAndCacheMatchesByDate(date: String): List<Match>
    suspend fun getCachedMatches(): List<Match>
    suspend fun fetchMatchesByLiveNow(): List<Match>
    suspend fun fetchMatchEvents(fixtureId: Int): List<MatchEvents>
}