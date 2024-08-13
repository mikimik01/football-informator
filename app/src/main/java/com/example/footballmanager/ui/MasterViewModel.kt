package com.example.footballmanager.ui

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballmanager.DAYS_OFFSET
import com.example.footballmanager.R
import com.example.footballmanager.data.MatchesDataSource
import com.example.footballmanager.data.entities.Match
import com.example.footballmanager.data.network.api.RetrievingDataState
import com.example.footballmanager.ui.screens.login.firebase.AuthService
import com.example.footballmanager.ui.bottom_navigation.MainScreens
import com.example.footballmanager.ui.headers.HeaderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class MasterViewModel @Inject constructor(
    private val matchesDataSource: MatchesDataSource,
    private val authService: AuthService

) : ViewModel() {
    var retrievingByDateState: RetrievingDataState by mutableStateOf(RetrievingDataState.Loading)
        private set
    var retrievingByLiveNowState: List<Match> by mutableStateOf(listOf())
        private set

    var currentBotNavSelection: MainScreens by mutableStateOf(MainScreens.Home)

    private val _currentSelectedHeader = mutableStateOf(HeaderType.HomeHeader)
    val currentSelectedHeader:State<HeaderType> = _currentSelectedHeader

    fun changeHeader(headerType: HeaderType) {
        _currentSelectedHeader.value = headerType
    }

    fun getFixturesLiveNow() {
        viewModelScope.launch {
            runCatching {
                val result = matchesDataSource.fetchMatchesByLiveNow()
                retrievingByLiveNowState = result
            }
        }
    }

    fun getFixturesData(date: String = LocalDate.now().toString(), ctx: Context) {
        viewModelScope.launch {

            retrievingByDateState = runCatching {
                val fetchResult = matchesDataSource.fetchAndCacheMatchesByDate(date = date)
                if (fetchResult.isEmpty()) {
                    getCached(ctx)
                } else {
                    RetrievingDataState.Success(matches = fetchResult, cached = false)
                }
            }.getOrElse {
                getCached(ctx)
            }
        }
    }

    private suspend fun getCached(ctx: Context): RetrievingDataState {
        val getCachedResult = matchesDataSource.getCachedMatches()
        return if (getCachedResult.isEmpty()) {
            RetrievingDataState.Error(ctx.getString(R.string.error_hint_internet_connection))
        } else {
            RetrievingDataState.Success(matches = getCachedResult, cached = true)
        }
    }


    private fun getDateProperties(currentTime: LocalDate): RetrievedData {
        val formatter = DateTimeFormatter.ofPattern("dd")
        val month = currentTime.month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
        val dayOfWeek = currentTime.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
        val day = currentTime.format(formatter)
        val fullyFormattedDate = "$day $month"
        return RetrievedData(dayOfWeek, currentTime.toString(), fullyFormattedDate)
    }


    fun get7DatesToDisplay(): List<RetrievedData> {
        val retList = mutableListOf<RetrievedData>()
        for (daysOffset in -DAYS_OFFSET..DAYS_OFFSET) {
            val currentTime = LocalDate.now().plusDays(daysOffset.toLong())
            retList.add(getDateProperties(currentTime))
        }
        return retList
    }

    fun getOneMoreDay(whichDay: Int): RetrievedData {
        val currentTime = LocalDate.now().plusDays((DAYS_OFFSET + whichDay).toLong())
        return getDateProperties(currentTime)
    }

    fun logOut() {
        authService.signOut()
    }

}

data class RetrievedData(
    val dayOfWeek: String, val localDate: String, val restOfDate: String
)



