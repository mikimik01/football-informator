package com.example.footballmanager.data

import com.example.footballmanager.data.cache.MatchDao
import com.example.footballmanager.data.entities.Match
import com.example.footballmanager.data.entities.MatchEvent
import com.example.footballmanager.data.network.api.FootballApiService
import javax.inject.Inject

class MatchesRepository @Inject constructor(
    private val footballApiService: FootballApiService, private val matchDao: MatchDao
) : MatchesDataSource {
    override suspend fun fetchAndCacheMatchesByDate(date: String): List<Match> {
        val matches = footballApiService.getMatchesByDate(date = date).matches
        matchDao.insertAll(matches)
        return matches
    }

    override suspend fun getCachedMatches(): List<Match> {
        return matchDao.getCachedMatches()
    }

    override suspend fun fetchMatchesByLiveNow(): List<Match> {
        return footballApiService.getMatchesByLiveNow().matches
    }

    override suspend fun fetchMatchEvents(fixtureId: Int): List<MatchEvent> {
        return footballApiService.getMatchEvents(fixtureId).matchEvents
    }
}