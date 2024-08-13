package com.example.footballmanager

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.footballmanager.data.cache.MatchDao
import com.example.footballmanager.data.cache.MatchesRoomDatabase
import com.example.footballmanager.data.entities.Away
import com.example.footballmanager.data.entities.Fixture
import com.example.footballmanager.data.entities.Goals
import com.example.footballmanager.data.entities.Home
import com.example.footballmanager.data.entities.League
import com.example.footballmanager.data.entities.Match
import com.example.footballmanager.data.entities.Teams
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MatchDaoInstrumentedTest {

    private lateinit var database: MatchesRoomDatabase
    private lateinit var matchDao: MatchDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MatchesRoomDatabase::class.java
        ).allowMainThreadQueries().build()
        matchDao = database.matchDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun `insertMatchIntoDatabaseAndRetrieve`() = runBlocking {
        val match = Match(
            id = 1,
            teams = Teams(
                home = Home(id = 1, name = "Team A"),
                away = Away(id = 2, name = "Team B")
            ),
            goals = Goals(home = 2, away = 1),
            league = League(name = "Premier League"),
            fixture = Fixture(date = "2024-01-01T14:00:00Z")
        )

        matchDao.insertAll(listOf(match))
        val retrievedMatch = matchDao.getCachedMatches()

        assertNotNull(retrievedMatch)
        assertEquals("Team A", retrievedMatch[0].teams?.home?.name)
    }
}
