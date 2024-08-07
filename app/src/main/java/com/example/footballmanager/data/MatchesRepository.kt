package com.example.footballmanager.data

import com.example.footballmanager.data.cache.MatchDao
import com.example.footballmanager.data.network.FootballApiService
import javax.inject.Inject

class MatchesRepository @Inject constructor(
    private val footballApiService: FootballApiService,
    private val matchDao: MatchDao
) {
//    suspend fun fetchAndCacheFixturesByDate(date: String): FixtureDataWrapper{
//        val fixtures = footballApiService.getFixturesByDate(date = date)
//        fixtureDao.insertAll(fixtures)
//        return fixtures
//    }

    //suspend fun fetchFixturesByLiveNow(): Fix
}