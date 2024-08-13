package com.example.footballmanager

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.footballmanager.data.cache.MatchesRoomDatabase
import com.example.footballmanager.data.entities.Away
import com.example.footballmanager.data.entities.Fixture
import com.example.footballmanager.data.entities.Goals
import com.example.footballmanager.data.entities.Home
import com.example.footballmanager.data.entities.League
import com.example.footballmanager.data.entities.Match
import com.example.footballmanager.data.entities.Teams
import com.example.footballmanager.ui.MasterViewModel
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDate

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun `test JSON mapping to Match entity`() {
        val json = """
    {
        "teams": {
            "home": {
                "id": 1,
                "name": "Team A",
                "logo": "http://logoA.com",
                "winner": "true"
            },
            "away": {
                "id": 2,
                "name": "Team B",
                "logo": "http://logoB.com",
                "winner": "false"
            }
        },
        "goals": {
            "home": 2,
            "away": 1
        },
        "league": {
            "name": "Premier League",
            "logo": "http://leagueLogo.com"
        },
        "fixture": {
            "status": {
                "short": "FT"
            },
            "date": "2024-01-01T14:00:00Z"
        }
    }
    """.trimIndent()

        val match = Gson().fromJson(json, Match::class.java)

        assertEquals("Team A", match.teams?.home?.name)
        assertEquals("Premier League", match.league?.name)
        assertEquals(2, match.goals?.home)
        assertEquals("FT", match.fixture?.status?.short)
    }

}