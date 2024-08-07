package com.example.footballmanager.data

import com.example.footballmanager.data.cache.MatchDao
import com.example.footballmanager.data.entities.Match
import com.example.footballmanager.data.network.FootballApiService
import javax.inject.Inject

class MatchesRepository @Inject constructor(
    private val footballApiService: FootballApiService,
    private val matchDao: MatchDao
) {
    suspend fun fetchAndCacheMatchesByDate(date: String): List<Match> {
        val matches = footballApiService.getMatchesByDate(date = date).matches
        matchDao.insertAll(matches)
        return matches
    }

    suspend fun getCachedMatches(): List<Match> {
        return matchDao.getCachedMatches()
    }

    suspend fun fetchMatchesByLiveNow(): List<Match> {
        val matches = footballApiService.getMatchesByLiveNow().matches
        return matches
    }
}